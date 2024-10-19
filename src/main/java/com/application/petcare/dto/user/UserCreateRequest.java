package com.application.petcare.dto.user;

import com.application.petcare.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCreateRequest {

    @NotBlank(message = "Name is required")
    private String name;

    private String userImg;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Cellphone is required")
    private String cellphone;

    @NotNull(message = "Role is required")
    private Role role;

    @NotBlank(message = "Street is required")
    private String street;

    @NotNull(message = "Number is required")
    private Integer number;  // Corrigido para @NotNull

    @NotBlank(message = "Complement is required")
    private String complement;

    @NotBlank(message = "CEP is required")
    @Pattern(regexp = "\\d{8}", message = "CEP should be 8 digits")
    private String cep;

    @NotBlank(message = "District is required")
    private String district;

    @NotBlank(message = "City is required")
    private String city;

    private String cnpjOwner;

    private String roleEmployee;

    private Boolean disponibilityStatus;

    private String cpfClient;
}
