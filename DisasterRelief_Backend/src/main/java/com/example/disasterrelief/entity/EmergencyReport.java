package com.example.disasterrelief.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import com.example.disasterrelief.Enum.EmergencyType;
import com.example.disasterrelief.Enum.ReportStatus;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data

@Table(name = "EmergencyReport")
public class EmergencyReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reportId;

    @Column(nullable = false)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 50)
    private EmergencyType type;

    @Enumerated(EnumType.STRING) // This is the crucial part!
    @Column(name = "status", length = 50)
    private ReportStatus status;

    @Column
    private Double latitude;
    @Column
    private Double longitude;

    @Column(columnDefinition = "TEXT")
    private String description;

    @CreationTimestamp
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CitizenID")

    private Citizen citizen;

    @OneToMany(mappedBy = "emergencyReport", cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    private List<Incident> incidents = new ArrayList<>();

    public EmergencyReport() {}
}
