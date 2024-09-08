package com.application.petcare.dto.owner;

import com.application.petcare.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerResponse {

    private UUID id;
    private String name;
    private String email;
    private String cnpj;
    private List<UUID> employeeIds;
    private Role role;
}