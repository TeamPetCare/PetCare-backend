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
    private Double priece;
    private Time estimatedTime;
    private Boolean disponibility;
    private List<Integer> scheduleIds;
}