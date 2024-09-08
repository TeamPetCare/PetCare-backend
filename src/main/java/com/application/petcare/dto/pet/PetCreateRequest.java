package com.application.petcare.dto.pet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class PetCreateRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Customer ID is required")
    private UUID customerId;

    @NotNull(message = "Especie ID is required")
    private UUID especieId;

    @NotNull(message = "Raca ID is required")
    private UUID racaId;

    @NotNull(message = "Birth date is required")
    private LocalDate birthDate;

    @NotBlank(message = "Sexo is required")
    private String sexo;

    @NotBlank(message = "Color is required")
    private String color;

    @NotNull(message = "Weight is required")
    private double weight;

    @NotNull(message = "Size ID is required")
    private UUID sizeId;

    private String notes;
}
