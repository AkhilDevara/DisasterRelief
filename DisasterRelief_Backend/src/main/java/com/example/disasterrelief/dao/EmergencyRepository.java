package com.example.disasterrelief.dao;

import com.example.disasterrelief.entity.EmergencyReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyRepository extends JpaRepository<EmergencyReport, Integer> {
}