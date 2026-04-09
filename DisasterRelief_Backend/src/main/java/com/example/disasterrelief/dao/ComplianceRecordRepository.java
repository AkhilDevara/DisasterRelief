package com.example.disasterrelief.dao;

import com.example.disasterrelief.entity.ComplianceRecord;
import com.example.disasterrelief.entity.User;
import com.example.disasterrelief.Enum.ComplianceResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplianceRecordRepository extends JpaRepository<ComplianceRecord, Integer> {
    List<ComplianceRecord> findByOfficer(User officer);
    List<ComplianceRecord> findByResult(ComplianceResult result);
    List<ComplianceRecord> findByEntityId(Integer entityId);
}