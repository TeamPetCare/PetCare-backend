package com.application.petcare.dto.schedule;

import com.application.petcare.entities.PaymentModel;
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
    private Integer review;
    private StatusAgendamento scheduleStatus;
    private LocalDateTime scheduleDate;
    private LocalTime scheduleTime;
    private LocalDateTime creationDate;
    private String petName;
    private String paymentLink;
    private PaymentStatus paymentStatus;
    private String scheduleNote;
    private PaymentMethod paymentMethod;
    private List<Services> services;
}
