package com.application.petcare.dto.schedule;

import com.application.petcare.entities.Payment;
import com.application.petcare.entities.Pet;
import com.application.petcare.entities.Services;
import com.application.petcare.enums.StatusAgendamento;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Getter
@Builder
@Setter
@Data
@AllArgsConstructor
public class ScheduleCreateRequest {
    @NotNull(message = "Schedule status is required")
    private StatusAgendamento scheduleStatus;

    @NotNull(message = "Schedule date is required")
    private LocalDateTime scheduleDate;

    @NotNull(message = "Schedule time is required")
    private LocalTime scheduleTime;

    @NotNull(message = "Creation date is required")
    private LocalDateTime creationDate;

    @NotBlank(message = "Schedule note is required")
    private String scheduleNote;

    @NotNull(message = "Pet is required")
    private Integer petId;

    @NotNull(message = "Payment is required")
    private Integer paymentId;

    @NotNull(message = "Service is required")
    private List<Integer> serviceIds;

    @NotNull(message = "Employee is required")
    private Integer employeeId;
}
