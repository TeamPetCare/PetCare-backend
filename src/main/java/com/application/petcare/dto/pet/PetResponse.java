package com.application.petcare.dto.pet;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PetResponse {
    private Integer id;
    private String name;
    private Integer userId;
    private Integer especieId;
    private Integer racaId;
    private LocalDate birthDate;
    private String sexo;
    private String color;
    private double weight;
    private Integer sizeId;
    private String notes;
}
