package com.dangDog.dangDog.dto;

public class CreateReportRequest {
    private long reportTypeId;
    private String code;

    public long getReportTypeId() {
        return reportTypeId;
    }

    public void setReportTypeId(long reportTypeId) {
        this.reportTypeId = reportTypeId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
