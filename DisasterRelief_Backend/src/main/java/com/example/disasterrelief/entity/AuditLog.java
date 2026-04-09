package com.example.disasterrelief.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "AuditLog")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer auditLogId;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;

    private String action;
    private String resource;
    private LocalDateTime timestamp;
    private String ipAddress;
    private String details;
}