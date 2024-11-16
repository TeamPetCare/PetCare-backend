package com.application.petcare.dto.user;

import com.application.petcare.enums.Role;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class UserCustomerUpdateRequest {
    private Integer id;

    @NotBlank(message = "Client name is required")
    private String cliente;

    @Pattern(regexp = "\\d+", message = "WhatsApp should contain only digits")
    private String WhatsApp;

    @NotBlank(message = "Street is required")
    private String rua;

    @NotNull(message = "Number is required")
    private Integer numero;

    @NotBlank(message = "District is required")
    private String bairro;

    private String complemento;

    private String cep;

    private Integer numeroDePets;

    private String dtUltimoAgendamento;

    private Integer totalAgendamentos;
}
