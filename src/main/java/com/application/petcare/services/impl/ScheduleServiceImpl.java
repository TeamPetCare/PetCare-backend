package com.application.petcare.services.impl;

import com.application.petcare.dto.schedule.ScheduleCreateRequest;
import com.application.petcare.dto.schedule.ScheduleResponse;
import com.application.petcare.dto.schedule.ScheduleStatsResponse;
import com.application.petcare.entities.Schedule;
import com.application.petcare.entities.User;
import com.application.petcare.enums.Role;
import com.application.petcare.enums.StatusAgendamento;
import com.application.petcare.exceptions.BadRoleException;
import com.application.petcare.exceptions.DuplicateScheduleException;
import com.application.petcare.exceptions.ResourceNotFoundException;
import com.application.petcare.repository.*;
import com.application.petcare.services.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private ScheduleRepository scheduleRepository;

    private PetRepository petRepository;
    private PaymentRepository paymentRepository;
    private ServicesRepository servicesRepository;
    private UserRepository userRepository;

    @Override
    public ScheduleResponse createSchedule(ScheduleCreateRequest request) {

        User possibleEmployee = userRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if(possibleEmployee.getRole().equals(Role.ROLE_CUSTOMER)){
            throw new BadRoleException("User is a customer");
        }

        LocalDateTime startDate = request.getScheduleDate();
        LocalDateTime endDate = request.getScheduleDate()
                        .plusHours(request.getScheduleTime().getHour())
                        .plusMinutes(request.getScheduleTime().getMinute());
//
//        List<Schedule> existingSchedule = scheduleRepository.findByScheduleDateBetweenAndEmployeeId(startDate, endDate, request.getEmployeeId());
//        if(!existingSchedule.isEmpty()){
//            throw new DuplicateScheduleException("Schedule with this employee in the same period already exists");
//        }


Schedule schedule = Schedule.builder()
        .scheduleStatus(request.getScheduleStatus())
        .scheduleDate(request.getScheduleDate())
        .scheduleTime(request.getScheduleTime())
        .creationDate(request.getCreationDate())
        .scheduleNote(request.getScheduleNote())
        .deletedAt(null)
        .pet(petRepository.findById(request.getPetId())
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found")))
        .payment(request.getPaymentId() == null 
                ? null 
                : paymentRepository.findById(request.getPaymentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Payment not found")))
        .services(servicesRepository.findAllByIdInAndDeletedAtIsNull(request.getServiceIds()))
        .employee(possibleEmployee)
        .build();


            Schedule savedSchedule = scheduleRepository.save(schedule);

        return mapToResponse(savedSchedule);
    }

    @Override
    public ScheduleResponse updateSchedule(Integer id, ScheduleCreateRequest request) {

        User possibleEmployee = userRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if(possibleEmployee.getRole().equals(Role.ROLE_CUSTOMER)){
            throw new BadRoleException("User is a customer");
        }

        LocalDateTime startDate = request.getScheduleDate();
        LocalDateTime endDate = request.getScheduleDate()
                .plusHours(request.getScheduleTime().getHour())
                .plusMinutes(request.getScheduleTime().getMinute());

//        List<Schedule> existingSchedule = scheduleRepository.findByScheduleDateBetweenAndEmployeeId(startDate, endDate, request.getEmployeeId());
//        if(!existingSchedule.isEmpty()){
//            throw new DuplicateScheduleException("Schedule with this employee in the same period already exists");
//        }

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
        schedule.setServices(servicesRepository.findAllByIdInAndDeletedAtIsNull(request.getServiceIds()));
        schedule.setEmployee(possibleEmployee);

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
        return scheduleRepository.findAllByDeletedAtIsNull().stream().map(this::mapToResponse).toList();
    }

    @Override
    public List<Schedule> findAllMonthlySchedules(LocalDateTime month) {
        LocalDateTime startMonth = month.withDayOfMonth(1);
        LocalDateTime start = startMonth.minusDays(7);
        LocalDateTime end = startMonth.plusDays(37);
        return scheduleRepository.findByScheduleDateBetween(start, end);
    }

    @Override
    public void deleteScheduleById(Integer id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found"));
        schedule.setDeletedAt(LocalDateTime.now());
        scheduleRepository.save(schedule);
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

    public List<ScheduleResponse> findSchedulesByDateAndTimeAndService(
            LocalDateTime date, LocalTime startTime, LocalTime endTime, Integer serviceId) {

        List<Schedule> schedules = scheduleRepository
                .findByScheduleDateAndScheduleTimeBetweenAndServicesId(date, startTime, endTime, serviceId);

        return schedules.stream().map(this::mapToResponse).toList();
    }




    public ScheduleResponse mapToResponse(Schedule schedule){

        Integer[] serviceIds = new Integer[schedule.getServices().size()];
        for (int i = 0; i < serviceIds.length; i++) {
            serviceIds[i] = schedule.getServices().get(i).getId();
        }

        return ScheduleResponse.builder()
                .id(schedule.getId())
                .scheduleStatus(schedule.getScheduleStatus())
                .scheduleDate(schedule.getScheduleDate())
                .scheduleTime(schedule.getScheduleTime())
                .creationDate(schedule.getCreationDate())
                .scheduleNote(schedule.getScheduleNote())
                .petId(schedule.getPet().getId())
                .paymentId(schedule.getPayment().getId())
                .serviceIds(List.of(serviceIds))
                .employeeId(schedule.getEmployee().getId())
                .build();
    }
}
