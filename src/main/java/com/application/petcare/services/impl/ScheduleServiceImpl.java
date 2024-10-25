package com.application.petcare.services.impl;

import com.application.petcare.dto.schedule.ScheduleCreateRequest;
import com.application.petcare.dto.schedule.ScheduleResponse;
import com.application.petcare.entities.Schedule;
import com.application.petcare.exceptions.ResourceNotFoundException;
import com.application.petcare.repository.PaymentRepository;
import com.application.petcare.repository.PetRepository;
import com.application.petcare.repository.ScheduleRepository;
import com.application.petcare.services.ScheduleService;

import java.util.ArrayList;
import java.util.List;

public class ScheduleServiceImpl implements ScheduleService {

    private ScheduleRepository scheduleRepository;

    private PetRepository petRepository;
    private PaymentRepository paymentRepository;

    @Override
    public ScheduleResponse createSchedule(ScheduleCreateRequest request) {
        Schedule schedule = Schedule.builder()
                .scheduleStatus(request.getScheduleStatus())
                .scheduleDate(request.getScheduleDate())
                .scheduleTime(request.getScheduleTime())
                .creationDate(request.getCreationDate())
                .scheduleNote(request.getScheduleNote())
                .pet(petRepository.findAllByIdIn(request.getPetIds())
                        .filter(pets -> !pets.isEmpty())
                        .orElseThrow(() -> new ResourceNotFoundException("Pet not found")))
                .payment(paymentRepository.findById(request.getPaymentId())
                        .orElseThrow(() -> new ResourceNotFoundException("Payment not found")))
                .build();
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
        schedule.setPet(petRepository.findAllByIdIn(request.getPetIds())
                        .filter(pets -> !pets.isEmpty())
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found")));
        schedule.setPayment(paymentRepository.findById(request.getPaymentId())
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found")));

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


    public ScheduleResponse mapToResponse(Schedule schedule){

        List<Integer> petIDs = new ArrayList<>();
        for (int i = 0; i < schedule.getPet().size(); i++) {
            petIDs.add(schedule.getPet().get(i).getId());
        }

        return ScheduleResponse.builder()
                .scheduleStatus(schedule.getScheduleStatus())
                .scheduleDate(schedule.getScheduleDate())
                .scheduleTime(schedule.getScheduleTime())
                .creationDate(schedule.getCreationDate())
                .scheduleNote(schedule.getScheduleNote())
                .petIds(petIDs)
                .paymentId(schedule.getPayment().getId())
                .build();
    }
}
