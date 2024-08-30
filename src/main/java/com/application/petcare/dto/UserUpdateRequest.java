package com.application.petcare.dto;

import com.application.petcare.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserUpdateRequest {
    //DTO para atualizar Usuario
    @NotBlank(message = "Nome e obrigatorio")
    private String name;

    @Email(message = "Email deve ser valido")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Senha e obrigatoria")
    private String password;

    @NotNull(message = "Role e obrigatorio")
    private Role role;
}