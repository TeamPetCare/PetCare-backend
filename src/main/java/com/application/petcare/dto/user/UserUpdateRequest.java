package com.application.petcare.dto.user;

import com.application.petcare.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    private String email;
    private String password;
    private String street;
    private Integer number;
    private String complement;
    private String cep;
    private String district;
    private String city;
}
