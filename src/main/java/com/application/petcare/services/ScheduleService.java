package com.application.petcare.services;


import com.application.petcare.dto.schedule.ScheduleCreateRequest;
import com.application.petcare.dto.schedule.ScheduleGetAllSchedulesResponse;
import com.application.petcare.dto.schedule.ScheduleResponse;
import com.application.petcare.dto.schedule.ScheduleStatsResponse;
import com.application.petcare.entities.Schedule;
import com.application.petcare.entities.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface ScheduleService {
    ScheduleResponse createSchedule(ScheduleCreateRequest request);

    ScheduleResponse updateSchedule(Integer id, ScheduleCreateRequest request);

    ScheduleResponse findScheduleById(Integer id);

    List<Schedule> findClientSchedulesByUserId(Integer id, LocalDateTime month);

    List<Schedule> findAllClientSchedulesByUserId(Integer id);

    List<Schedule> findClientSchedulesByPetId(LocalDateTime month, Integer petId);

    List<ScheduleGetAllSchedulesResponse> findAllSchedules();

    List<Schedule> findAllMonthlySchedules(LocalDateTime month);

    void deleteScheduleById(Integer id);

    ScheduleStatsResponse getScheduleStats();

    List<ScheduleResponse> findSchedulesByDateAndTimeAndService(
            LocalDateTime date, LocalTime startTime, LocalTime endTime, Integer serviceId);

    public byte[] generateCsvFileSchedule();
}


