package com.application.petcare.dto.register;

import com.application.petcare.enums.Role;

import java.util.List;

public record RegisterRequestDto(String name,
                                 String userImg,
                                 String email,
                                 String password,
                                 String cellphone,
                                 Role role,
                                 String street,
                                 Integer number,
                                 String complement,
                                 String cep,
                                 String district,
                                 String city,
                                 String cnpjOwner,
                                 String roleEmployee,
                                 Boolean disponibilityStatus,
                                 String cpfClient,
                                 List<Integer>petIds) {
}
