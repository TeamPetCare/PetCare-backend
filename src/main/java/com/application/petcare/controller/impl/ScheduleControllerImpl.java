package com.application.petcare.controller.impl;

import com.application.petcare.controller.ScheduleController;
import com.application.petcare.dto.schedule.ScheduleCreateRequest;
import com.application.petcare.dto.schedule.ScheduleResponse;
import com.application.petcare.dto.schedule.ScheduleStatsResponse;
import com.application.petcare.entities.Schedule;
import com.application.petcare.services.ScheduleService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@AllArgsConstructor
public class ScheduleControllerImpl implements ScheduleController {

    private ScheduleService scheduleService;

    @Override
    public ResponseEntity<ScheduleResponse> createSchedule(ScheduleCreateRequest scheduleCreateRequest) {
        return ResponseEntity.status(201).body(scheduleService.createSchedule(scheduleCreateRequest));
    }

    @Override
    public ResponseEntity<ScheduleResponse> getScheduleById(Integer id) {
        return ResponseEntity.ok(scheduleService.findScheduleById(id));
    }

    @Override
    public ResponseEntity<List<ScheduleResponse>> getAllSchedules() {
        return ResponseEntity.ok(scheduleService.findAllSchedules());
    }

    @Override
    public ResponseEntity<List<Schedule>> getAllMonthlySchedules() {
        return ResponseEntity.ok().body(scheduleService.findAllMonthlySchedules());
    }

    @Override
    public ResponseEntity<ScheduleResponse> updateSchedule(Integer id, ScheduleCreateRequest scheduleCreateRequest) {
        return ResponseEntity.ok(scheduleService.updateSchedule(id, scheduleCreateRequest));
    }

    @Override
    public ResponseEntity<Void> deleteSchedule(Integer id) {
        scheduleService.deleteScheduleById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ScheduleStatsResponse> getScheduleStats() {
        return ResponseEntity.ok(scheduleService.getScheduleStats());
    }


    @Override
    public ResponseEntity<List<ScheduleResponse>> getSchedulesByDateAndTimeAndService(
            LocalDateTime date, LocalTime startTime, LocalTime endTime, Integer serviceId) {

        List<ScheduleResponse> schedules = scheduleService
                .findSchedulesByDateAndTimeAndService(date, startTime, endTime, serviceId);

        return ResponseEntity.ok(schedules);
    }
}
