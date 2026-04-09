package com.example.disasterrelief.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.cognizant.disastermanagement.Enum.ComplianceResult;
import org.cognizant.disastermanagement.Enum.ComplainceType;
import org.cognizant.disastermanagement.dao.ComplianceRecordRepository;
import org.cognizant.disastermanagement.dao.UserRepository;
import org.cognizant.disastermanagement.dto.request.ComplianceRecordRequestDTO;
import org.cognizant.disastermanagement.dto.response.ComplianceRecordResponseDTO;
import org.cognizant.disastermanagement.entity.ComplianceRecord;
import org.cognizant.disastermanagement.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ComplianceRecordServiceTest {

    @Mock
    private ComplianceRecordRepository repository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ComplianceRecordService complianceService;

    private User mockOfficer;
    private ComplianceRecordRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        // Mock User (Officer)
        mockOfficer = new User();
        mockOfficer.setUserId(101);

        // Setup a valid request DTO
        requestDTO = ComplianceRecordRequestDTO.builder()
                .entityId(500)
                .type(ComplainceType.RELIEF)
                .officerId(101)
                .notes("Standard check")
                .result(null) // Leave null to test default logic
                .build();
    }

    @Test
    @DisplayName("Create Record - Success with Default Status")
    void testCreateRecord_Success() {
        // Arrange
        when(userRepository.findById(101)).thenReturn(Optional.of(mockOfficer));

        ComplianceRecord savedEntity = ComplianceRecord.builder()
                .complianceId(1)
                .entityId(500)
                .type(ComplainceType.RELIEF)
                .result(ComplianceResult.PENDINGREVIEW) // Service should set this
                .officer(mockOfficer)
                .date(LocalDateTime.now())
                .build();

        when(repository.save(any(ComplianceRecord.class))).thenReturn(savedEntity);

        // Act
        ComplianceRecordResponseDTO response = complianceService.createRecord(requestDTO);

        // Assert
        assertNotNull(response);
        assertEquals(ComplianceResult.PENDINGREVIEW, response.getResult());
        assertEquals(101, response.getOfficerId());
        verify(repository, times(1)).save(any(ComplianceRecord.class));
    }

    @Test
    @DisplayName("Create Record - Throws Exception when Officer not found")
    void testCreateRecord_OfficerNotFound() {
        // Arrange
        when(userRepository.findById(101)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            complianceService.createRecord(requestDTO);
        });

        assertEquals("Officer not found with ID: 101", exception.getMessage());
        verify(repository, never()).save(any(ComplianceRecord.class));
    }

    @Test
    @DisplayName("Update Record - Success")
    void testUpdateRecord_Success() {
        // Arrange
        ComplianceRecord existingRecord = new ComplianceRecord();
        existingRecord.setComplianceId(1);
        existingRecord.setResult(ComplianceResult.PENDINGREVIEW);

        ComplianceRecordRequestDTO updateRequest = ComplianceRecordRequestDTO.builder()
                .result(ComplianceResult.COMPLIANT)
                .notes("Fixes verified")
                .build();

        when(repository.findById(1)).thenReturn(Optional.of(existingRecord));
        when(repository.save(any(ComplianceRecord.class))).thenReturn(existingRecord);

        // Act
        ComplianceRecordResponseDTO response = complianceService.updateRecord(1, updateRequest);

        // Assert
        assertEquals(ComplianceResult.COMPLIANT, response.getResult());
        assertEquals("Fixes verified", response.getNotes());
        verify(repository).save(existingRecord);
    }
}