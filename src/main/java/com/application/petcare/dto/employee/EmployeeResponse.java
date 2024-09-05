package com.application.petcare.dto.employee;

import com.application.petcare.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private UUID id;
    private String name;
    private String email;
    private Role role;
    private String cargo;
}
