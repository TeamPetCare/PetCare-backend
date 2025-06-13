package com.application.petcare.dto.schedule;

import com.application.petcare.entities.Services;
import com.application.petcare.enums.PaymentMethod;
import com.application.petcare.enums.PaymentStatus;
import com.application.petcare.enums.StatusAgendamento;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class ScheduleDetailsResponseDTO {
    private Integer id;
    private StatusAgendamento scheduleStatus;
    private LocalDateTime scheduleDate;
    private LocalTime scheduleTime;
    private String petName;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private String scheduleNote;
    private List<Services> services;
}
