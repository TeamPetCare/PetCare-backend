package com.application.petcare.dto.schedule;

import com.application.petcare.enums.PaymentStatus;
import com.application.petcare.enums.StatusAgendamento;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Builder
@Setter
@Data
@AllArgsConstructor
public class SchedulesAllTimeClientResponse {
    private Integer id;
    private Integer petId;
    private String petName;
    private Integer userId;
    private LocalDateTime scheduleDate;
    private LocalTime scheduleTime;
    private PaymentStatus paymentStatus;
    private StatusAgendamento scheduleStatus;
    private LocalDateTime deletedAt;
    private Integer review;
}
