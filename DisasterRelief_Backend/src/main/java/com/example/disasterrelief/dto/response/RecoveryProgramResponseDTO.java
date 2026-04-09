package com.example.disasterrelief.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDate;
import java.util.List;
import com.example.disasterrelief.Enum.RecoveryStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // Useful for manual mapping in the Controller
public class RecoveryProgramResponseDTO {
    private int programId;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private double budget;
    private RecoveryStatus status;

    // List of nested resources
    private List<ResourceResponseDTO> resources;
}