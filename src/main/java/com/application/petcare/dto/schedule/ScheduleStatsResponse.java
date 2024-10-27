package com.application.petcare.dto.schedule;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ScheduleStatsResponse {
    private Integer totalAgendados;
    private Integer totalConcluidos;
    private Integer totalCancelados;
}
