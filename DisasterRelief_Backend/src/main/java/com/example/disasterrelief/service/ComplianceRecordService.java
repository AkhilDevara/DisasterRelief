package com.example.disasterrelief.service;

import com.example.disasterrelief.dao.ComplianceRecordRepository;
import com.example.disasterrelief.dao.UserRepository;
import com.example.disasterrelief.dto.request.ComplianceRecordRequestDTO;
import com.example.disasterrelief.dto.response.ComplianceRecordResponseDTO;
import com.example.disasterrelief.entity.ComplianceRecord;
import com.example.disasterrelief.entity.User;
import com.example.disasterrelief.Enum.ComplianceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComplianceRecordService {

    @Autowired
    private ComplianceRecordRepository repository;

    @Autowired
    private UserRepository userRepository;

    public ComplianceRecordResponseDTO createRecord(ComplianceRecordRequestDTO request) {
        User officer = null;
        if (request.getOfficerId() != null) {
            officer = userRepository.findById(request.getOfficerId())
                    .orElseThrow(() -> new RuntimeException("Officer not found"));
        }

        // Logic: Default to PendingReview if result is not provided
        ComplianceResult finalResult = (request.getResult() == null)
                ? ComplianceResult.PENDINGREVIEW : request.getResult();

        ComplianceRecord record = ComplianceRecord.builder()
                .entityId(request.getEntityId())
                .type(request.getType())
                .result(finalResult)
                .date(LocalDateTime.now())
                .officer(officer)
                .notes(request.getNotes())
                .build();

        return mapToResponse(repository.save(record));
    }

    public List<ComplianceRecordResponseDTO> getAllRecords() {
        return repository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ComplianceRecordResponseDTO updateRecord(Integer id, ComplianceRecordRequestDTO request) {
        ComplianceRecord record = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compliance Record not found"));

        if (request.getOfficerId() != null) {
            User officer = userRepository.findById(request.getOfficerId())
                    .orElseThrow(() -> new RuntimeException("Officer not found"));
            record.setOfficer(officer);
        }

        record.setResult(request.getResult());
        record.setNotes(request.getNotes());

        return mapToResponse(repository.save(record));
    }

    private ComplianceRecordResponseDTO mapToResponse(ComplianceRecord record) {
        return ComplianceRecordResponseDTO.builder()
                .complianceId(record.getComplianceId())
                .entityId(record.getEntityId())
                .type(record.getType())
                .result(record.getResult())
                .date(record.getDate())
                .officerId(record.getOfficer() != null ? record.getOfficer().getUserId() : null)
                .notes(record.getNotes())
                .build();
    }
}