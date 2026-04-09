package com.example.disasterrelief.entity;

import jakarta.persistence.*;
import lombok.*;
import com.example.disasterrelief.Enum.shelterStatus;
import java.time.LocalDateTime;
import java.util.Vector;

@Getter // Lombok generates all getters
@Setter // Lombok generates all setters
@Entity
@Table(name="Shelter")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Shelter {

    public int getShelterId() {
        return shelterId;
    }

    public void setShelterId(int shelterId) {
        this.shelterId = shelterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    public shelterStatus getStatus() {
        return status;
    }

    public void setStatus(shelterStatus status) {
        this.status = status;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shelterId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Location")
    private String location;

    @Column(name = "Latitude")
    private Double latitude;

    @Column(name = "Longitude")
    private Double longitude;

    @Column(name = "Capacity")
    private int capacity;

    @Column(name = "Occupancy")
    private int occupancy;

    @Column(name = "Status")
    private shelterStatus status;

    @Column(name = "ContactInfo")
    private String contactInfo;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;

    @Column(name= "UpdatedAt")
    private LocalDateTime updatedAt;

}
