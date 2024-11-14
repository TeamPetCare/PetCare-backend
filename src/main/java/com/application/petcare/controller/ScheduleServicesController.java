package com.application.petcare.controller;

import com.application.petcare.dto.ScheduleServicesCreateRequest;
import com.application.petcare.entities.Services;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "ScheduleService", description = "Criar um agendamento e serviço ao mesmo tempo")
@RequestMapping("admin/api/schedule-services")
public interface ScheduleServicesController {
    @Operation(summary = "Criar um novo agendamento e serviço")
    @PostMapping
    ResponseEntity<Services> createScheduleServices(@RequestBody ScheduleServicesCreateRequest scheduleServicesCreateRequest);
}
