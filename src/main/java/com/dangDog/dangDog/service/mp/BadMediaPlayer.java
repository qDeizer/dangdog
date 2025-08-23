package com.dangDog.dangDog.service.mp;

public class BadMediaPlayer {
    public String playVideo(String path, String ext) {
        switch (ext) {
            case "mp4":
                return new MP4Player().play(path);
            case "avi":
                return new AviPlayer().play(path);
            default:
                throw new IllegalStateException("Format not supported");
        }
    }
}
