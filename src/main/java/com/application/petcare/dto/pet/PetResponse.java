package com.application.petcare.dto.pet;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class PetResponse {
    private UUID id;
    private String name;
    private UUID customerId;
    private UUID especieId;
    private UUID racaId;
    private LocalDate birthDate;
    private String sexo;
    private String color;
    private double weight;
    private UUID sizeId;
    private String notes;
}
