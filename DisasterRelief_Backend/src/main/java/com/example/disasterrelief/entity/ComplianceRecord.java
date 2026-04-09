package com.example.disasterrelief.entity;

import jakarta.persistence.*;
import lombok.*;
import com.example.disasterrelief.Enum.ComplianceResult;
import com.example.disasterrelief.Enum.ComplainceType;

import java.time.LocalDateTime;

@Entity
@Table(name = "ComplianceRecord")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplianceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ComplianceID")
    private Integer complianceId;

    @Column(name = "EntityID", nullable = false)
    private Integer entityId;

    @Enumerated(EnumType.STRING)
    @Column(name = "Type", nullable = false)
    private ComplainceType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "Result", nullable = false)
    private ComplianceResult result;

    @Column(name = "Date", nullable = false)
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OfficerID")
    private User officer;

    @Column(name = "Notes", columnDefinition = "TEXT")
    private String notes;
}