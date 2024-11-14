package com.application.petcare.controller;

import com.application.petcare.dto.schedule.ScheduleCreateRequest;
import com.application.petcare.dto.schedule.ScheduleResponse;
import com.application.petcare.dto.schedule.ScheduleStatsResponse;
import com.application.petcare.entities.Schedule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Tag(name = "Schedule", description = "Gerenciar agendamentos")
@RequestMapping("admin/api/schedules")
public interface ScheduleController {
    @Operation(summary = "Criar um novo agendamento")
    @PostMapping
    ResponseEntity<ScheduleResponse> createSchedule(@RequestBody ScheduleCreateRequest scheduleCreateRequest);

    @Operation(summary = "Buscar agendamento por ID")
    @GetMapping("/{id}")
    ResponseEntity<ScheduleResponse> getScheduleById(@PathVariable Integer id);

    @Operation(summary = "Buscar todos os agendamentos")
    @GetMapping
    ResponseEntity<List<ScheduleResponse>> getAllSchedules();

    @Operation(summary = "Buscar todos os agendamentos mensais")
    @GetMapping("/monthly-schedules")
    ResponseEntity<List<Schedule>> getAllMonthlySchedules();

    @Operation(summary = "Atualiza agendamento por ID")
    @PutMapping("/{id}")
    ResponseEntity<ScheduleResponse> updateSchedule(
            @PathVariable Integer id,
            @RequestBody ScheduleCreateRequest scheduleCreateRequest);

    @Operation(summary = "Excluir agendamento por ID")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteSchedule(@PathVariable Integer id);


    @Operation(summary = "Obter estatísticas de agendamentos")
    @GetMapping("/stats")
    ResponseEntity<ScheduleStatsResponse> getScheduleStats();

    @Operation(summary = "Buscar agendamentos por data, hora de início, hora de fim e serviço")
    @GetMapping("/filter-agendamento")
    ResponseEntity<List<ScheduleResponse>> getSchedulesByDateAndTimeAndService(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
            @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime,
            @RequestParam("serviceId") Integer serviceId
    );

}
