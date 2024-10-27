package com.application.petcare.services.impl;

import com.application.petcare.dto.schedule.ScheduleCreateRequest;
import com.application.petcare.dto.schedule.ScheduleResponse;
import com.application.petcare.dto.schedule.ScheduleStatsResponse;
import com.application.petcare.entities.Schedule;
import com.application.petcare.enums.StatusAgendamento;
import com.application.petcare.exceptions.DuplicateScheduleException;
import com.application.petcare.exceptions.ResourceNotFoundException;
import com.application.petcare.repository.PaymentRepository;
import com.application.petcare.repository.PetRepository;
import com.application.petcare.repository.ScheduleRepository;
import com.application.petcare.repository.ServicesRepository;
import com.application.petcare.services.ScheduleService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private ScheduleRepository scheduleRepository;

    private PetRepository petRepository;
    private PaymentRepository paymentRepository;
    private ServicesRepository servicesRepository;

    @Override
    public ScheduleResponse createSchedule(ScheduleCreateRequest request) {
        Schedule schedule = Schedule.builder()
                .scheduleStatus(request.getScheduleStatus())
                .scheduleDate(request.getScheduleDate())
                .scheduleTime(request.getScheduleTime())
                .creationDate(request.getCreationDate())
                .scheduleNote(request.getScheduleNote())
                .pet(petRepository.findById(request.getPetId())
                        .orElseThrow(() -> new ResourceNotFoundException("Pet not found")))
                .payment(paymentRepository.findById(request.getPaymentId())
                        .orElseThrow(() -> new ResourceNotFoundException("Payment not found")))
                .services(servicesRepository.findAllByIdIn(request.getServiceIds())
                        .filter(services -> !services.isEmpty())
                        .orElseThrow(() -> new ResourceNotFoundException("Service not found")))
                .build();

        Optional<Schedule> existingSchedule = scheduleRepository.findByScheduleDateAndScheduleTime(schedule.getScheduleDate(), schedule.getScheduleTime());
        if (existingSchedule.isPresent()) {
            throw new DuplicateScheduleException("Um agendamento para esta data e horário já existe.");
        }
            Schedule savedSchedule = scheduleRepository.save(schedule);

        return mapToResponse(savedSchedule);
    }

    @Override
    public ScheduleResponse updateSchedule(Integer id, ScheduleCreateRequest request) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found"));
        schedule.setScheduleStatus(request.getScheduleStatus());
        schedule.setScheduleDate(request.getScheduleDate());
        schedule.setScheduleTime(request.getScheduleTime());
        schedule.setCreationDate(request.getCreationDate());
        schedule.setScheduleNote(request.getScheduleNote());
        schedule.setPet(petRepository.findById(request.getPetId())
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found")));
        schedule.setPayment(paymentRepository.findById(request.getPaymentId())
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found")));
        schedule.setServices(servicesRepository.findAllByIdIn(request.getServiceIds())
                .filter(services -> !services.isEmpty())
                .orElseThrow(() -> new ResourceNotFoundException("Service not found")));

        Schedule updatedSchedule = scheduleRepository.save(schedule);
        return mapToResponse(updatedSchedule);
    }

    @Override
    public ScheduleResponse findScheduleById(Integer id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found"));
        return mapToResponse(schedule);
    }

    @Override
    public List<ScheduleResponse> findAllSchedules() {
        return scheduleRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    @Override
    public void deleteScheduleById(Integer id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found"));
        scheduleRepository.deleteById(id);
    }

    @Override
    public ScheduleStatsResponse getScheduleStats() {
        int totalAgendados = (int) scheduleRepository.count();
        int totalConcluidos = (int) scheduleRepository.countByScheduleStatus(StatusAgendamento.CONCLUIDO);
        int totalCancelados = (int) scheduleRepository.countByScheduleStatus(StatusAgendamento.CANCELADO);

        return ScheduleStatsResponse.builder()
                .totalAgendados(totalAgendados)
                .totalConcluidos(totalConcluidos)
                .totalCancelados(totalCancelados)
                .build();
    }


    public ScheduleResponse mapToResponse(Schedule schedule){

        Integer[] serviceIds = new Integer[schedule.getServices().size()];
        for (int i = 0; i < serviceIds.length; i++) {
            serviceIds[i] = schedule.getServices().get(i).getId();
        }

        return ScheduleResponse.builder()
                .scheduleStatus(schedule.getScheduleStatus())
                .scheduleDate(schedule.getScheduleDate())
                .scheduleTime(schedule.getScheduleTime())
                .creationDate(schedule.getCreationDate())
                .scheduleNote(schedule.getScheduleNote())
                .petId(schedule.getPet().getId())
                .paymentId(schedule.getPayment().getId())
                .serviceIds(List.of(serviceIds))
                .build();
    }
}
