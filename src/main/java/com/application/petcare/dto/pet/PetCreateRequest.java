package com.application.petcare.dto.pet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class PetCreateRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Color is required")
    private String color;

    @NotNull(message = "Estimated weight is required")
    private double estimatedWeight;

    @NotNull(message = "Birthdate is required")
    private LocalDate birthdate;

    @NotBlank(message = "Pet observations are required")
    private String petObservations;

    private String petImg;

    @NotNull(message = "Plan ID is required")
    private Integer planId;

    @NotNull(message = "Specie ID is required")
    private Integer specieId;

    @NotNull(message = "Race ID is required")
    private Integer raceId;

    @NotNull(message = "Size ID is required")
    private Integer sizeId;

    @NotNull(message = "User ID is required")
    private Integer userId;
}
