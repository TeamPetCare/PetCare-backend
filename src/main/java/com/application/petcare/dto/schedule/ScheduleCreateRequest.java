package com.application.petcare.dto.schedule;

import com.application.petcare.entities.Payment;
import com.application.petcare.entities.Pet;
import com.application.petcare.entities.Services;
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
public class ScheduleCreateRequest {
    @NotNull(message = "Schedule status is required")
    private String scheduleStatus;

    @NotNull(message = "Schedule date is required")
    private LocalDateTime scheduleDate;

    @NotNull(message = "Schedule time is required")
    private Time scheduleTime;

    @NotNull(message = "Creation date is required")
    private LocalDateTime creationDate;

    @NotBlank(message = "Schedule note is required")
    private String scheduleNote;

    @NotNull(message = "Pet is required")
    private List<Integer> petIds;

    @NotNull(message = "Payment is required")
    private Integer paymentId;
}
