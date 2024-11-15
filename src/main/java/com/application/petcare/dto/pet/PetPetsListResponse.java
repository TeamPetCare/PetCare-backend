package com.application.petcare.dto.pet;

import com.application.petcare.entities.Plans;
import com.application.petcare.entities.Race;
import com.application.petcare.entities.Size;
import com.application.petcare.entities.Specie;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class PetPetsListResponse {
    private Integer id;
    private String name;
    private String gender;
    private String color;
    private double estimatedWeight;
    private LocalDate birthdate;
    private String petObservations;
    private String petImg;
    private Plans plan;
    private Specie specie;
    private Race race;
    private Size size;
    private Integer userId;
    private LocalDateTime lastSchedule;
    private Integer totalSchedules;
}
