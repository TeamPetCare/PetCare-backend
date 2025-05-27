package com.application.petcare.services.impl;

import com.application.petcare.dto.schedule.*;
import com.application.petcare.entities.Schedule;
import com.application.petcare.entities.User;
import com.application.petcare.enums.Role;
import com.application.petcare.enums.StatusAgendamento;
import com.application.petcare.exceptions.BadRoleException;
import com.application.petcare.exceptions.ResourceNotFoundException;
import com.application.petcare.repository.*;
import com.application.petcare.services.ScheduleService;
import com.opencsv.CSVWriter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private ScheduleRepository scheduleRepository;

    private PetRepository petRepository;
    private PaymentModelRepository paymentModelRepository;
    private ServicesRepository servicesRepository;
    private UserRepository userRepository;

    @Override
    public ScheduleResponse createSchedule(ScheduleCreateRequest request) {

        User possibleEmployee = userRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (possibleEmployee.getRole().equals(Role.ROLE_CUSTOMER)) {
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
                        : paymentModelRepository.findById(request.getPaymentId())
                        .orElseThrow(() -> new ResourceNotFoundException("Payment not found")))
                .services(servicesRepository.findAllByIdInAndDeletedAtIsNull(request.getServiceIds()))
                .employee(possibleEmployee)
                .review(request.getReview())
                .build();


        Schedule savedSchedule = scheduleRepository.save(schedule);

        return mapToResponse(savedSchedule);
    }

    @Override
    public ScheduleResponse updateSchedule(Integer id, ScheduleCreateRequest request) {

        User possibleEmployee = userRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (possibleEmployee.getRole().equals(Role.ROLE_CUSTOMER)) {
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
        schedule.setPayment(request.getPaymentId() == null
                ? null
                : paymentModelRepository.findById(request.getPaymentId())
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found")));
        schedule.setServices(servicesRepository.findAllByIdInAndDeletedAtIsNull(request.getServiceIds()));
        schedule.setEmployee(possibleEmployee);
        schedule.setDeletedAt(request.getDeletedAt());
        schedule.setReview(request.getReview());

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
    public List<Schedule> findClientSchedulesByUserId(Integer id, LocalDateTime month) {
        LocalDateTime startMonth = month.withDayOfMonth(1);
        LocalDateTime start = startMonth.minusDays(7);
        LocalDateTime end = startMonth.plusDays(37);
        return scheduleRepository.findByScheduleDateBetweenAndPetUserId(start, end, id);
    }

    @Override
    public List<SchedulesAllTimeClientResponse> findAllClientSchedulesByUserId(Integer id) {
        return scheduleRepository.findByPetUserId(id).stream().map(this::mapToAllTimeClientResponse).toList();
    }

    @Override
    public List<Schedule> findClientSchedulesByPetId(LocalDateTime month, Integer petId) {
        LocalDateTime startMonth = month.withDayOfMonth(1);
        LocalDateTime start = startMonth.minusDays(7);
        LocalDateTime end = startMonth.plusDays(37);
        return scheduleRepository.findByScheduleDateBetweenAndPetId(start, end, petId);
    }

    @Override
    public List<ScheduleGetAllSchedulesResponse> findAllSchedules() {
        return scheduleRepository.findAllByDeletedAtIsNull().stream().map(this::mapToScheduleGetAllSchedulesResponse).toList();
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

    public byte[] generateCsvFileSchedule() {
        List<Schedule> list = scheduleRepository.findAllByDeletedAtIsNull();
        return writeCsvFileSchedule(list);
    }

    public SchedulesAllTimeClientResponse mapToAllTimeClientResponse(Schedule schedule){
        return SchedulesAllTimeClientResponse.builder()
                .id(schedule.getId())
                .userId(schedule.getPet().getUser().getId())
                .scheduleStatus(schedule.getScheduleStatus())
                .scheduleDate(schedule.getScheduleDate())
                .scheduleTime(schedule.getScheduleTime())
                .petId(schedule.getPet().getId())
                .petName(schedule.getPet().getName())
                .paymentStatus(schedule.getPayment() != null ? schedule.getPayment().getPaymentStatus() : null)
                .review(schedule.getReview())
                .build();
    }


    public ScheduleResponse mapToResponse(Schedule schedule) {
        // Obter os IDs dos serviços associados ao Schedule
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
                .deletedAt(schedule.getDeletedAt())
                // Verificação de nulidade para evitar NullPointerException
                .paymentId(schedule.getPayment() != null ? schedule.getPayment().getId() : null)
                .serviceIds(List.of(serviceIds))
                .employeeId(schedule.getEmployee().getId())
                .review(schedule.getReview())
                .build();
    }

    public ScheduleGetAllSchedulesResponse mapToScheduleGetAllSchedulesResponse(Schedule schedule) {

        List<String> serviceNames = new ArrayList<>();
        List<Double> servicePrices = new ArrayList<>();
        List<Integer> serviceIds = new ArrayList<>();

        for (int i = 0; i < schedule.getServices().size(); i++) {
            serviceNames.add(schedule.getServices().get(i).getName());
            servicePrices.add(schedule.getServices().get(i).getPrice());
            serviceIds.add(schedule.getServices().get(i).getId());
        }

        return ScheduleGetAllSchedulesResponse.builder()
                .id(schedule.getId())
                .scheduleStatus(schedule.getScheduleStatus())
                .scheduleDate(schedule.getScheduleDate())
                .scheduleTime(schedule.getScheduleTime())
                .creationDate(schedule.getCreationDate())
                .scheduleNote(schedule.getScheduleNote())
                .serviceNames(serviceNames)
                .servicePrices(servicePrices)
                .userCelphoneNumber(schedule.getPet().getUser().getCellphone())
                .userName(schedule.getPet().getUser().getName())
                .petName(schedule.getPet().getName())
                .petImg(schedule.getPet().getPetImg())
                .payment(schedule.getPayment())
                .userId(schedule.getPet().getUser().getId())
                .petId(schedule.getPet().getId())
                .serviceIds(serviceIds)
                .review(schedule.getReview())
                .build();
    }

    private byte[] writeCsvFileSchedule(List<Schedule> lista) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");


        try (CSVWriter writer = new CSVWriter(new OutputStreamWriter(byteArrayOutputStream, StandardCharsets.UTF_8),
                ';', // Delimitador
                CSVWriter.DEFAULT_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END)) {


            // Cabeçalho do CSV
            String[] header = {"ID", "Status Agendamento", "Data agendamento", "Tempo agendamento", "Data criação", "Obsercação Agendamento", "Nome Cliente", "Nome Pet", "Raça", "Nascimento", "Porte", "Observações", "Valor Pagamento", "Nome funcionario"};
            writer.writeNext(header);

            for (Schedule schedule : lista) {
                String[] record = {
                        String.valueOf(schedule.getId()),
                        schedule.getScheduleStatus() != null ? schedule.getScheduleStatus().name() : "",
                        schedule.getScheduleDate() != null ? schedule.getScheduleDate().format(formatter) : "",
                        schedule.getScheduleTime() != null ? schedule.getScheduleTime().format(timeFormatter) : "",
                        schedule.getCreationDate() != null ? schedule.getCreationDate().format(formatter) : "",
                        schedule.getScheduleNote() != null ? schedule.getScheduleNote() : "",
                        schedule.getPet().getUser() != null ? schedule.getPet().getUser().getName() : "",
                        schedule.getPet() != null ? schedule.getPet().getName() : "",
                        schedule.getPet().getRace() != null ? schedule.getPet().getRace().getRaceType() : "",
                        schedule.getPet().getBirthdate() != null ? schedule.getPet().getBirthdate().format(dateFormatter) : "",
                        schedule.getPet().getSize() != null ? schedule.getPet().getSize().getSizeType() : "",
                        schedule.getPet().getPetObservations() != null ? schedule.getPet().getPetObservations() : "",
                        schedule.getPayment() != null && schedule.getPayment().getPrice() != null ?
                                String.valueOf(schedule.getPayment().getPrice()) : "",
                        schedule.getEmployee() != null ? schedule.getEmployee().getName() : "",
                        schedule.getReview() != null ? String.valueOf(schedule.getReview()) : ""
                };
                writer.writeNext(record);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return byteArrayOutputStream.toByteArray();
    }

}
