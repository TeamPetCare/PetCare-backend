package com.application.petcare.dto.schedule;

import com.application.petcare.entities.Payment;
import com.application.petcare.entities.Pet;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Builder
@Setter
@Data
@AllArgsConstructor
public class ScheduleResponse {
    private Integer id;
    private String scheduleStatus;
    private LocalDateTime scheduleDate;
    private Time scheduleTime;
    private LocalDateTime creationDate;
    private String scheduleNote;
    private List<Integer> petIds;
    private Integer paymentId;
}
