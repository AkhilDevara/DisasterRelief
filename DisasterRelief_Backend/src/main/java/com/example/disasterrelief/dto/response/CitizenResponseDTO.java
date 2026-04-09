package com.example.disasterrelief.dto.response;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

import com.example.disasterrelief.Enum.Gender;
import com.example.disasterrelief.Enum.CitizenStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitizenResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer citizenId;
    private String name;
    private LocalDate dob;
    private Gender gender;
    private String address;
    private String contactInfo;
    private CitizenStatus status;
}