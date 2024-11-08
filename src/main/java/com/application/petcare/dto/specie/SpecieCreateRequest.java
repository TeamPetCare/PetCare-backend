package com.application.petcare.dto.specie;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SpecieCreateRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Priece is required")
    private Double price;
}