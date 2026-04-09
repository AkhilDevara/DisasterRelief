package com.example.disasterrelief.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;

public enum Role {
    Citizen, Officer, Manager, Admin, Compliance, Auditor;

    @JsonCreator
    public static Role fromString(String value) {
        // This handles "Manager", "manager", or "MANAGER" from Postman
        return Stream.of(Role.values())
                .filter(r -> r.name().equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}