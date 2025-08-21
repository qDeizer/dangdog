package com.dangDog.dangDog.entity;

import jakarta.persistence.*;

@Entity
@Table
public class ReportDetailKeysEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private long reportTypeId;
    @Column
    private String code;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
