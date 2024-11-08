package com.application.petcare.dto.race;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RaceCreateRequest {

    @NotBlank(message = "Race type is required")
    private String raceType;

    @NotNull(message = "Priece is required")
    private Double price;
}