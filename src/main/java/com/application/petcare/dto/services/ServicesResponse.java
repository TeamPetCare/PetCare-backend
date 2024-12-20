package com.application.petcare.dto.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicesResponse {
    private Integer id;
    private String nome;
    private String note;
    private Double price;
    private Time estimatedTime;
    private Boolean disponibility;
    private Boolean isExclusive;
//    private List<Integer> scheduleIds;
}
