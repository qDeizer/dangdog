package com.dangDog.dangDog.repository;

import com.dangDog.dangDog.entity.ReportDetailKeysEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportDetailKeysRepository extends JpaRepository<ReportDetailKeysEntity, Long> {
}
