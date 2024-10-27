package com.application.petcare.dto.schedule;

import com.application.petcare.entities.Payment;
import com.application.petcare.entities.Pet;
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
public class ScheduleResponse {
    private Integer id;
    private StatusAgendamento scheduleStatus;
    private LocalDateTime scheduleDate;
    private LocalTime scheduleTime;
    private LocalDateTime creationDate;
    private String scheduleNote;
    private Integer petId;
    private Integer paymentId;
    private List<Integer> serviceIds;
}
