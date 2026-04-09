package com.example.disasterrelief.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import com.example.disasterrelief.Enum.IncidentStatus;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Incident")
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int incidentId;

    @Column(nullable = false, length = 1000)
    private String actions;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IncidentStatus status;

    @CreationTimestamp
    private LocalDateTime date;

    // MANY incidents → ONE emergency report
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ReportID")
    private EmergencyReport emergencyReport;

    // MANY incidents → ONE officer (User role)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OfficerID")
    private User officer;

    public Incident() {}

    // GETTERS AND SETTERS
//
//    public int getIncidentId() { return incidentId; }
//    public void setIncidentId(int incidentId) { this.incidentId = incidentId; }
//
//    public String getActions() { return actions; }
//    public void setActions(String actions) { this.actions = actions; }
//
//    public IncidentStatus getStatus() { return status; }
//    public void setStatus(IncidentStatus status) { this.status = status; }
//
//    public LocalDateTime getDate() { return date; }
//
//    public EmergencyReport getEmergencyReport() { return emergencyReport; }
//    public void setEmergencyReport(EmergencyReport emergencyReport) { this.emergencyReport = emergencyReport; }
//
//    public User getOfficer() { return officer; }
//    public void setOfficer(User officer) { this.officer = officer; }
}