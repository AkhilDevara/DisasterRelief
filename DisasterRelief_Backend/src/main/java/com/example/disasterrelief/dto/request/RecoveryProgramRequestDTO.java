package com.example.disasterrelief.dto.request;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecoveryProgramRequestDTO {

    @NotBlank(message = "Title is mandatory")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    /* Regex Breakdown:
       (?=.*[a-zA-Z]) -> "Lookahead": Must contain at least one letter.
       [a-zA-Z0-9\s]* -> "Body": Allows any combination of letters, numbers, and spaces.
    */
    @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9\\s]*$",
            message = "Title can include numbers (e.g., 'Flood 2026'), but cannot be numbers only.")
    private String title;

    @NotBlank(message = "Description is mandatory")
    // Applies the same logic to Description to ensure it's not just a string of digits
    @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9\\s\\.,]*$",
            message = "Description must contain text, not just numbers.")
    private String description;

    private LocalDate startDate;
    private LocalDate endDate;

    @Positive(message = "Budget must be greater than zero")
    private double budget;
}



//
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.AllArgsConstructor;
//import java.time.LocalDate;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Positive;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class RecoveryProgramRequestDTO {
//
//    @NotBlank(message = "Title is mandatory")
//    private String title;
//
//    private String description;
//
//    private LocalDate startDate;
//
//    private LocalDate endDate;
//
//    @Positive(message = "Budget must be greater than zero")
//    private double budget;
//}