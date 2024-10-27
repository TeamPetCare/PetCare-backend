package com.application.petcare.controller;

import com.application.petcare.dto.schedule.ScheduleCreateRequest;
import com.application.petcare.dto.schedule.ScheduleResponse;
import com.application.petcare.dto.schedule.ScheduleStatsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Schedule", description = "Gerenciar agendamentos")
@RequestMapping("/api/schedules")
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
}
