package com.application.petcare.dto.pet;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PetResponse {
    private Integer id;
    private String name;
    private String gender;
    private String color;
    private double estimatedWeight;
    private LocalDate birthdate;
    private String petObservations;
    private String petImg;
    private Integer planId;
    private Integer specieId;
    private Integer raceId;
    private Integer sizeId;
    private Integer userId;
}
