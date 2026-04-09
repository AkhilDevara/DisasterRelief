package com.example.disasterrelief.entity;

import lombok.*;
import com.example.disasterrelief.Enum.distributionStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "distribution")
@Data                       // Generates getters, setters, toString, etc.
@NoArgsConstructor          // Required by JPA
@AllArgsConstructor// Required by @Builder
@Getter
@Setter
@Builder(toBuilder = true)
public class Distribution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DistributionID") // CRITICAL: Matches your SQL PK exactly
    private Integer distributionId;

    @Column(name = "ItemID", nullable = false)
    private Integer itemId;

    @Column(name = "CitizenID")
    private Integer citizenId;

    @Column(name = "OfficerID")
    private Integer officerId;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "Date") // CRITICAL: Matches your SQL 'Date' column
    private LocalDateTime date;

    @Column(name = "Status") // CRITICAL: Matches your SQL 'Status' column
    @Enumerated(EnumType.STRING)
    private distributionStatus status;

    @Column(name = "notes") // Matches your lowercase 'notes' in schema
    private String notes;

    // Getters and Setters
//    public int getDistributionId() {
//        return distributionId;
//    }
//
//    public void setDistributionId(int distributionId) {
//        this.distributionId = distributionId;
//    }
//
//    public int getItemId() {
//        return itemId;
//    }
//
//    public void setItemId(int itemId) {
//        this.itemId = itemId;
//    }
//
//    public int getCitizenId() {
//        return citizenId;
//    }
//
//    public void setCitizenId(int citizenId) {
//        this.citizenId = citizenId;
//    }
//
//    public int getOfficerId() {
//        return officerId;
//    }
//
//    public void setOfficerId(int officerId) {
//        this.officerId = officerId;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    public LocalDateTime getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDateTime date) {
//        this.date = date;
//    }
//
//    public String getNotes() {
//        return notes;
//    }
//
//    public void setNotes(String notes) {
//        this.notes = notes;
//    }
//
//    public distributionStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(distributionStatus status) {
//        this.status = status;
//    }
}