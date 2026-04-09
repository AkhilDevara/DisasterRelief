package com.example.disasterrelief.dto.response;

import lombok.*;
import com.example.disasterrelief.Enum.ComplianceResult;
import com.example.disasterrelief.Enum.ComplainceType;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComplianceRecordResponseDTO {
    private Integer complianceId;
    private Integer entityId;
    private ComplainceType type;
    private ComplianceResult result;
    private LocalDateTime date;
    private Integer officerId;
    private String notes;
}