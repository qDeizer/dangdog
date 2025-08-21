@echo off
setlocal

:: Bu batch dosyası, kendi içine gömülü olan PowerShell script'ini çalıştırır.
:: Bu yöntem, dosya kilitleme ve karmaşık dosya yolu hatalarını çözmek için daha güvenilirdir.
powershell -NoProfile -ExecutionPolicy Bypass -Command "& { $script = Get-Content -Raw -Encoding utf8 '%~f0'; $block = [regex]::Match($script, '(?ms)^#<PS_SCRIPT_START>.*#<PS_SCRIPT_END>').Value; Invoke-Expression $block; exit $LASTEXITCODE }"

:: PowerShell script'inin çıkış kodunu kontrol et
if %ERRORLEVEL% EQU 0 (
    echo.
    echo Dosyalar basariyla yazildi! 1 saniye sonra pencere kapanacak...
    timeout /t 1 /nobreak >nul
    exit /b 0
) else (
    echo.
    echo Hata olustu! Detaylar icin yukaridaki mesajlari kontrol edin.
    echo Pencereyi kapatmak icin bir tusa basin...
    pause >nul
    exit /b %ERRORLEVEL%
)


#<PS_SCRIPT_START>
# ============================================================================
#                         POWERSHELL SNAPSHOT SCRIPT'I
# ============================================================================

# Hataları takip etmek için Continue kullan (SilentlyContinue yerine)
$ErrorActionPreference = "Continue"

# Script'in çalıştığı dizini proje ana dizini olarak kabul et
$projectRoot = Get-Location

# --- AYARLAR ---

# Hariç tutulacak spesifik ve tam klasör yolları (proje kök dizinine göre)
$excludedPathPrefixes = @(
)

# Projenin herhangi bir yerinde bulunursa hariç tutulacak klasör ADLARI
$excludedDirs = @(

)

# Hariç tutulacak dosya uzantıları
$excludedExtensions = @(
    # Görüntü ve Medya Dosyaları

)

# Hariç tutulacak belirli dosya adları
$excludedFiles = @(

)

$snapshotBasename = 'ProjectDangDogSnapshot_v'


# --- SÜRÜM KONTROLÜ ---
$latestVersion = Get-ChildItem -Path $projectRoot -Filter "$($snapshotBasename)*.txt" | ForEach-Object {
    if ($_.Name -match 'v(\d+)\.txt$') {
        [int]$matches[1]
    }
} | Measure-Object -Maximum | Select-Object -ExpandProperty Maximum

if ($null -eq $latestVersion) { $latestVersion = 0 }
$nextVersion = $latestVersion + 1
$outputFile = Join-Path -Path $projectRoot -ChildPath "$($snapshotBasename)$($nextVersion).txt"

Write-Host "Proje anlik goruntusu olusturuluyor: $outputFile" -ForegroundColor Cyan
Write-Host "Lutfen bekleyin..."


# --- DOSYA İŞLEME (KİLİTLENMEYE KARŞI İYİLEŞTİRİLMİŞ YÖNTEM) ---
$outputStream = $null
$outputWriter = $null

try {
    # Çıktı dosyasını yazma modunda aç. Bu sayede dosya tek seferde açılır ve kilitlenmez.
    $outputStream = New-Object System.IO.FileStream($outputFile, [System.IO.FileMode]::Create, [System.IO.FileAccess]::Write, [System.IO.FileShare]::Read)
    $outputWriter = New-Object System.IO.StreamWriter($outputStream, [System.Text.Encoding]::UTF8)

    # Proje dizinindeki tüm dosyaları ve alt dizinlerdekileri al
    Get-ChildItem -Path $projectRoot -Recurse -File | ForEach-Object {
        $file = $_
        $includeFile = $true
        $relativePath = $file.FullName.Substring($projectRoot.Path.Length).TrimStart([System.IO.Path]::DirectorySeparatorChar)

        # --- FİLTRELEME KONTROLLERİ ---

        if ($file.Extension -eq '.bat' -or $file.FullName -eq (Resolve-Path $outputFile).Path) {
            $includeFile = $false
        }
        if ($includeFile -and ($excludedFiles -contains $file.Name -or $excludedExtensions -contains $file.Extension)) {
            $includeFile = $false
        }
        if ($includeFile) {
            foreach ($prefix in $excludedPathPrefixes) {
                if ($relativePath.StartsWith($prefix, [System.StringComparison]::OrdinalIgnoreCase)) {
                    $includeFile = $false; break
                }
            }
        }
        if ($includeFile) {
            $pathSegments = $file.FullName.Split([System.IO.Path]::DirectorySeparatorChar)
            foreach ($segment in $pathSegments) {
                if ($excludedDirs -contains $segment) {
                    $includeFile = $false; break
                }
            }
        }
        
        # Dosya snapshot'a dahil edilecekse...
        if ($includeFile) {
            $header = @"

--------------------------------------------------------------------------------
DOSYA: $($relativePath)
--------------------------------------------------------------------------------

"@
            # Add-Content yerine doğrudan açık olan stream'e yaz
            $outputWriter.Write($header)

            # DOSYA OKUMA
            try {
                $stream = New-Object System.IO.FileStream($file.FullName, [System.IO.FileMode]::Open, [System.IO.FileAccess]::Read, [System.IO.FileShare]::ReadWrite)
                $reader = New-Object System.IO.StreamReader($stream, [System.Text.Encoding]::UTF8, $true)
                $content = $reader.ReadToEnd()
                $reader.Dispose()
                $stream.Dispose()
                
                # Add-Content yerine doğrudan açık olan stream'e yaz
                $outputWriter.Write($content)
            } catch {
                $errorMessage = "HATA: $($file.FullName) dosyasi okunamadi. Sebep: $($_.Exception.Message)"
                Write-Host $errorMessage -ForegroundColor Red
                # Hata mesajını da stream'e yaz
                $outputWriter.Write($errorMessage)
            }
        }
    }
}
finally {
    # Her durumda stream'leri kapat ve kaynakları serbest bırak
    if ($null -ne $outputWriter) { $outputWriter.Dispose() }
    if ($null -ne $outputStream) { $outputStream.Dispose() }
}


# Hata sayısını kontrol et ve uygun çıkış kodu ile bitir
$errorCount = $Error.Count
if ($errorCount -eq 0) {
    Write-Host "Islem basariyla tamamlandi." -ForegroundColor Green
    exit 0
} else {
    Write-Host "Islem tamamlandi ancak $errorCount hata olustu." -ForegroundColor Yellow
    exit 1
}

#<PS_SCRIPT_END>