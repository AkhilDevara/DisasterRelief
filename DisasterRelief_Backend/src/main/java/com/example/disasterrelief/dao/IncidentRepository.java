package com.example.disasterrelief.dao;

import com.example.disasterrelief.entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentRepository extends JpaRepository<Incident, Integer> {
}