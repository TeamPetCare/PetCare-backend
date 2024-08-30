package com.application.petcare.dto;

import com.application.petcare.enums.Role;
import lombok.Data;

import java.util.UUID;

@Data
public class UserResponse {
    //DTO para resposta
    private UUID id;
    private String name;
    private String email;
    private Role role;
}