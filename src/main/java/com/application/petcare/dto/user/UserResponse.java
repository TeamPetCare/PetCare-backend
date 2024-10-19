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
public class UserResponse {
    private Integer id;
    private String name;
    private String userImg;
    private String email;
    private String password;
    private String cellphone;
    private Role role;
    private String street;
    private Integer number;
    private String complement;
    private String cep;
    private String district;
    private String city;
    private String cnpjOwner;
    private String roleEmployee;
    private Boolean disponibilityStatus;
    private String cpfClient;

}