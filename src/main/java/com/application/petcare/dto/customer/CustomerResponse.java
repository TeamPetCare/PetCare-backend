package com.application.petcare.dto.customer;


import com.application.petcare.enums.Role;
import lombok.Data;

import java.util.UUID;

@Data
public class CustomerResponse {
    private UUID id;
    private String name;
    private String email;
    private Role role;
}