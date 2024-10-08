package com.application.petcare.dto.owner;

import com.application.petcare.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OwnerCreateRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotNull(message = "Role is required")
    private Role role;

    @NotBlank(message = "CNPJ is required")
    private String cnpj;

    @NotNull(message = "Employee IDs are required")
    private List<Integer> employeeIds = new ArrayList<>();  // ajuste
}