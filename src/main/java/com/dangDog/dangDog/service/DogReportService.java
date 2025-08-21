package com.dangDog.dangDog.service;

import com.dangDog.dangDog.dto.CreateReportRequest;
import com.dangDog.dangDog.entity.ReportDetailKeysEntity;
import com.dangDog.dangDog.repository.ReportDetailKeysRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DogReportService {
    private final ReportDetailKeysRepository reportDetailKeysRepository;

    public DogReportService(ReportDetailKeysRepository reportDetailKeysRepository) {
        this.reportDetailKeysRepository = reportDetailKeysRepository;
    }

    public Long createReport(CreateReportRequest request) {
        ReportDetailKeysEntity reportDetailKeysEntity = new ReportDetailKeysEntity();
        reportDetailKeysEntity.setCode(request.getCode());
        reportDetailKeysEntity.setReportTypeId(reportDetailKeysEntity.getReportTypeId());
        return reportDetailKeysRepository.save(reportDetailKeysEntity).getId();
    }

    public List<String> getReports() {
        List<String> response = new ArrayList<>();
        for (ReportDetailKeysEntity reportDetailKeysEntity : reportDetailKeysRepository.findAll()) {
            response.add(reportDetailKeysEntity.getCode());
        }
        return response;
        /**
         * Yukaridakinin kisa yolu asagida
         */
        //return reportDetailKeysRepository.findAll().stream().map(ReportDetailKeysEntity::getCode).toList();
    }
}
