package com.example.disasterrelief.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import com.example.disasterrelief.Enum.IncidentStatus;
@Data
public class IncidentRequestDTO {



    @NotNull(message = "Report ID is required")
    private Integer reportId;

    @NotNull(message = "Officer ID is required")
    private Integer officerId;

    @NotBlank(message = "Actions are required")
    private String actions;

    @NotNull(message = "Status is required")
    private IncidentStatus status;

    // Getters and Setters
}