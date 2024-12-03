package com.application.petcare.controller.impl;

import com.application.petcare.controller.ScheduleServicesController;
import com.application.petcare.dto.ScheduleServicesCreateRequest;
import com.application.petcare.entities.Schedule;
import com.application.petcare.entities.Services;
import com.application.petcare.exceptions.ResourceNotFoundException;
import com.application.petcare.repository.PaymentModelRepository;
import com.application.petcare.repository.PetRepository;
import com.application.petcare.repository.ScheduleRepository;
import com.application.petcare.repository.ServicesRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class ScheduleServicesControllerImpl implements ScheduleServicesController {

    private ServicesRepository servicesRepository;
    private ScheduleRepository scheduleRepository;

    private PaymentModelRepository paymentModelRepository;
    private PetRepository petRepository;

    @Override
    public ResponseEntity<Services> createScheduleServices(ScheduleServicesCreateRequest scheduleServicesCreateRequest) {
        // Criar e configurar o Service
        Services services = new Services();
        services.setName(scheduleServicesCreateRequest.getServiceName());
        services.setNote(scheduleServicesCreateRequest.getServiceNote());
        services.setPrice(scheduleServicesCreateRequest.getServicePrice());
        services.setEstimatedTime(scheduleServicesCreateRequest.getServiceEstimatedTime());
        services.setDisponibility(scheduleServicesCreateRequest.getServiceDisponibility());

        // Criar e configurar o Schedule
        Schedule schedule = new Schedule();
        schedule.setScheduleStatus(scheduleServicesCreateRequest.getScheduleStatus());
        schedule.setScheduleDate(scheduleServicesCreateRequest.getScheduleDate());
        schedule.setCreationDate(scheduleServicesCreateRequest.getScheduleDate());
        schedule.setScheduleTime(scheduleServicesCreateRequest.getScheduleTime());
        schedule.setCreationDate(scheduleServicesCreateRequest.getScheduleCreationDate());
        schedule.setScheduleNote(scheduleServicesCreateRequest.getScheduleNote());
        schedule.setPet(petRepository.findById(scheduleServicesCreateRequest.getPetId())
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found")));
        schedule.setPayment(paymentModelRepository.findById(scheduleServicesCreateRequest.getPaymentId())
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found")));

        // Adicionar o Schedule ao Service e vice-versa

//        Optional<Schedule> existingSchedule = scheduleRepository.findByScheduleDateAndScheduleTime(schedule.getScheduleDate(), schedule.getScheduleTime());
//        if (existingSchedule.isPresent()) {
//            throw new DuplicateScheduleException("Um agendamento para esta data e horário já existe.");
//        }

        List<Services> servicesList = new ArrayList<>();
        List<Schedule> schedules = new ArrayList<>();

//        services.setSchedules(schedules);
        schedule.setServices(servicesList);

        services = servicesRepository.save(services);
        schedule = scheduleRepository.save(schedule);

//        services.getSchedules().add(schedule);
        schedule.getServices().add(services);

        servicesRepository.save(services);
        scheduleRepository.save(schedule);

        return ResponseEntity.status(201).body(services);
    }

}
