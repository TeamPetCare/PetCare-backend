package com.application.petcare.dto.services;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Time;
import java.util.List;

@Data
public class ServicesCreateRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Note is required")
    private String note;

    @NotNull(message = "Priece is required")
    private Double price;

    @NotNull(message = "Estimated time is required")
    private Time estimatedTime;

    @NotNull(message = "Disponibility is required")
    private Boolean disponibility;

    @NotNull(message = "Is Exclusive is required")
    private Boolean isExclusive;

//    @NotNull(message = "Schedule is required")
//    private List<Integer> scheduleIds;

}
