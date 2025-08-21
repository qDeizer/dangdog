package com.dangDog.dangDog.controller;

import com.dangDog.dangDog.dto.CreateReportRequest;
import com.dangDog.dangDog.service.DogReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dog")
public class DogReportController {

    private final DogReportService dogReportService;

    public DogReportController(DogReportService dogReportService) {
        this.dogReportService = dogReportService;
    }

    @PostMapping("/create")
    public Long createReport(@RequestBody CreateReportRequest request) {
        return dogReportService.createReport(request);
    }

    @GetMapping("get")
    public List<String> getReports() {
        return dogReportService.getReports();
    }
}
