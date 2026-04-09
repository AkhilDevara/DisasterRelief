package com.example.disasterrelief.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserStatus {
    ACTIVE, INACTIVE, SUSPENDED;

//    @JsonCreator
//    public static UserStatus fromString(String value) {
//        if (value == null) return null;
//        return UserStatus.valueOf(value.toUpperCase());
//    }
}