package com.application.petcare.dto.schedule;

import com.application.petcare.entities.Payment;
import com.application.petcare.enums.StatusAgendamento;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class ScheduleGetAllSchedulesResponse {
    private Integer id;
    private StatusAgendamento scheduleStatus;
    private LocalDateTime scheduleDate;
    private LocalTime scheduleTime;
    private LocalDateTime creationDate;
    private String scheduleNote;

    private List<String> serviceNames;
    private List<Double> servicePrices;
    private String userCelphoneNumber;
    private String userName;
    private String petImg;
    private String petName;
    private Payment payment;

    private Integer employeeId;
    private Integer userId;
    private List<Integer> serviceIds;
    private Integer petId;
}
