package com.example.disasterrelief.dto.response;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer auditLogId;
    private String action;
    private String resource;
    private LocalDateTime timestamp;
    private String ipAddress;
    private String details;
}