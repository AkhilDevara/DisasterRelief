package com.example.disasterrelief.service;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.cognizant.disastermanagement.Enum.AuditStatus;
import org.cognizant.disastermanagement.dao.AuditRepository;
import org.cognizant.disastermanagement.dao.UserRepository;
import org.cognizant.disastermanagement.dto.request.AuditRequestDTO;
import org.cognizant.disastermanagement.dto.response.AuditResponseDTO;
import org.cognizant.disastermanagement.entity.Audit;
import org.cognizant.disastermanagement.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AuditServiceTest {

    @Mock
    private AuditRepository auditRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuditService auditService;

    private User mockOfficer;
    private AuditRequestDTO auditRequest;

    @BeforeEach
    void setUp() {
        // Initialize common test data
        mockOfficer = new User();
        mockOfficer.setUserId(101);

        auditRequest = AuditRequestDTO.builder()
                .officerId(101)
                .scope("Food Safety Check")
                .findings("All good")
                .status(null) // Testing the default logic
                .build();
    }

    @Test
    @DisplayName("Create Audit - Success Scenario")
    void testCreateAudit_Success() {
        // Arrange
        when(userRepository.findById(101)).thenReturn(Optional.of(mockOfficer));

        // Mock the save behavior
        Audit savedAudit = Audit.builder()
                .auditId(1)
                .officer(mockOfficer)
                .scope(auditRequest.getScope())
                .status(AuditStatus.SCHEDULED)
                .build();

        when(auditRepository.save(any(Audit.class))).thenReturn(savedAudit);

        // Act
        AuditResponseDTO response = auditService.createAudit(auditRequest);

        // Assert
        assertNotNull(response);
        assertEquals("Food Safety Check", response.getScope());
        assertEquals(AuditStatus.SCHEDULED, response.getStatus()); // Verify default status logic
        verify(auditRepository, times(1)).save(any(Audit.class));
    }

    @Test
    @DisplayName("Create Audit - Officer Not Found Throws Exception")
    void testCreateAudit_UserNotFound() {
        // Arrange
        when(userRepository.findById(101)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            auditService.createAudit(auditRequest);
        });

        assertEquals("Compliance Officer not found with ID: 101", exception.getMessage());
        verify(auditRepository, never()).save(any(Audit.class));
    }

    @Test
    @DisplayName("Delete Audit - Success")
    void testDeleteAudit() {
        // Act
        auditService.deleteAudit(1);

        // Assert
        verify(auditRepository, times(1)).deleteById(1);
    }
}