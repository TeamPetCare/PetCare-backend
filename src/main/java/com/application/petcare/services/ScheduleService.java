package com.application.petcare.services;


import com.application.petcare.dto.schedule.*;
import com.application.petcare.entities.Schedule;
import com.application.petcare.entities.User;
import com.application.petcare.enums.StatusAgendamento;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface ScheduleService {
    ScheduleResponse createSchedule(ScheduleCreateRequest request);

    ScheduleResponse updateSchedule(Integer id, ScheduleCreateRequest request);

    ScheduleResponse updateScheduleReview(Integer id, Integer review);

    ScheduleResponse updateScheduleStatus(Integer id, StatusAgendamento status);

    ScheduleResponse findScheduleById (Integer id);

    ScheduleDetailsResponseDTO getScheduleDetailsById(Integer id);

    List<Schedule> findClientSchedulesByUserId(Integer id, LocalDateTime month);

    List<SchedulesAllTimeClientResponse> findAllClientSchedulesByUserId(Integer id);

    List<Schedule> findClientSchedulesByPetId(LocalDateTime month, Integer petId);

    List<ScheduleGetAllSchedulesResponse> findAllSchedules();

    List<Schedule> findAllMonthlySchedules(LocalDateTime month);

    void deleteScheduleById(Integer id);

    ScheduleStatsResponse getScheduleStats();

    List<ScheduleResponse> findSchedulesByDateAndTimeAndService(
            LocalDateTime date, LocalTime startTime, LocalTime endTime, Integer serviceId);

    public byte[] generateCsvFileSchedule();
}


