package com.example.disasterrelief.dao;

import com.example.disasterrelief.entity.Audit;
import com.example.disasterrelief.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Integer> {
    // We find by the User object to respect the @ManyToOne relation
    List<Audit> findByOfficer(User officer);
}