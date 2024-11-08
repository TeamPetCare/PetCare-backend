package com.application.petcare.dto.race;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaceResponse {
    private Integer id;
    private String raceType;
    private Double price;
}