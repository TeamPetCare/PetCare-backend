package com.application.petcare.dto.customer;


import com.application.petcare.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CustomerResponse {
    private Integer id;
    private String name;
    private String email;
    private Role role;
    private String cpf;
}