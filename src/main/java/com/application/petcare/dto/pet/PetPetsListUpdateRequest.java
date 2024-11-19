package com.application.petcare.dto.pet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class PetPetsListUpdateRequest {
    @NotNull(message = "ID is required")
    private Integer id;

    @NotBlank(message = "Pet name is required")
    private String pet;

    @NotBlank(message = "Specie ID is required")
    private Integer especie;

    @NotBlank(message = "Gender is required")
    private String sexo;

    @NotBlank(message = "Race ID is required")
    private Integer raca;

    @NotNull(message = "Birthdate is required")
    private LocalDate dtNascimento;

    @NotBlank(message = "Size ID is required")
    private Integer porte;

    @NotNull(message = "Estimated weight is required")
    private Double pesoEstimado;

    @NotBlank(message = "Color is required")
    private String cor;

    @NotBlank(message = "Owner ID is required")
    private Integer dono;

    private String observacoes;

    private String dtUltimoAgendamento;

    @NotNull(message = "Total agendamentos is required")
    private Integer totalAgendamentos;

    private Integer plano;
}
