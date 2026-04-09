package com.example.disasterrelief.dto.request;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import com.example.disasterrelief.Enum.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CitizenRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Name cannot be blank")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name must contain only alphabets and spaces")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    @NotBlank(message = "Phone cannot be blank")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be exactly 10 digits")
    private String phone;

    @NotNull(message = "DOB cannot be null")
    private LocalDate dob;

    @NotNull(message = "Gender cannot be null")
    private Gender gender;

    @NotBlank(message = "Address cannot be blank")
    private String address;
}


//package com.example.disasterrelief.dto.request;
//
//import java.io.Serial;
//import java.io.Serializable;
//import java.time.LocalDate;
//
//import com.example.disasterrelief.Enum.Gender;
//import com.example.disasterrelief.Enum.CitizenStatus;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class CitizenRequestDTO{
//    private String name;
//    private LocalDate dob;
//    private Gender gender;
//    private String address;
//    private String contactInfo;
//
//}