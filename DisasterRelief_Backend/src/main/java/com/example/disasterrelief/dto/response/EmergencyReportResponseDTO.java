package com.example.disasterrelief.dto.response;

import lombok.Data;
import com.example.disasterrelief.Enum.EmergencyType;
import com.example.disasterrelief.Enum.ReportStatus;

import java.time.LocalDateTime;
@Data
public class EmergencyReportResponseDTO {



    private Integer reportId;
    private Integer citizenId;
    private EmergencyType type;
    private String location;
    private ReportStatus status;
    private LocalDateTime date;

    private Double latitude;
    private Double longitude;
    private String description;

    // Getters and Setters
}