package com.application.petcare.dto;

import com.application.petcare.enums.StatusAgendamento;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class ScheduleServicesCreateRequest {
    private String serviceName;
    private String serviceNote;
    private Double servicePrice;
    private Time serviceEstimatedTime;
    private Boolean serviceDisponibility;

    private StatusAgendamento scheduleStatus;
    private LocalDateTime scheduleDate;
    private LocalTime scheduleTime;
    private LocalDateTime scheduleCreationDate;
    private String scheduleNote;
    private Integer petId;
    private Integer paymentId;
}
