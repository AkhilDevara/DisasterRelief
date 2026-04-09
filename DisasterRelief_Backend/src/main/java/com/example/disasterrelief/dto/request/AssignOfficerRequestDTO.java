package com.example.disasterrelief.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AssignOfficerRequestDTO {

    @NotNull(message = "Officer ID is required")
    private Integer officerId;
}


//package com.example.disasterrelief.dto.request;
//
//import jakarta.validation.constraints.NotNull;
//import lombok.Data;
//
//@Data
//public class AssignOfficerRequestDTO {
//    @NotNull(message = "Officer ID is required")
//    private Integer officerId;
////    public Integer getOfficerId() {
////        return officerId;
////    }
////
////    public void setOfficerId(Integer officerId) {
////        this.officerId = officerId;
////    }
//
//
//
//    // Getter and Setter
//}