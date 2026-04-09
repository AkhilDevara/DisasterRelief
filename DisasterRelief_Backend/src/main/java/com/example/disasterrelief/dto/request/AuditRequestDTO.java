package com.example.disasterrelief.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.disasterrelief.Enum.AuditStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditRequestDTO {

    @NotNull(message = "Officer ID is required to link the audit")
    private Integer officerId;

    @NotBlank(message = "Scope is required (e.g., 'Food Distribution Q1')")
    private String scope;

    private String findings;

    // Optional: If null, the Service will set it to 'Scheduled'
    private AuditStatus status;
}