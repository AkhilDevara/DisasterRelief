package com.example.disasterrelief.dto.response;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.example.disasterrelief.Enum.Role;
import com.example.disasterrelief.Enum.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer userId;
    private String name;
    private Role role;
    private String email;
    private String phone;
    private UserStatus status;
    private LocalDateTime createdAt;
}