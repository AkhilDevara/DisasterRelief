package com.example.disasterrelief.dao;

import com.example.disasterrelief.entity.CitizenDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenDocumentRepository extends JpaRepository<CitizenDocument, Integer> {
}
