package com.example.disasterrelief.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

import com.example.disasterrelief.Enum.Gender;
import com.example.disasterrelief.Enum.CitizenStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Citizen")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Citizen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CitizenID")
    private Integer citizenId;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "DOB")
    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    @Column(name = "Gender")
    private Gender gender;

    @Column(name = "Address", length = 500)
    private String address;

    @Column(name = "ContactInfo")
    private String contactInfo;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private CitizenStatus status;

    @OneToOne
    @JoinColumn(name = "UserID")
    private User user;

    @OneToMany(mappedBy = "citizen", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CitizenDocument> documents;
}
