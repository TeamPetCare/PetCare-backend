package com.application.petcare.repository;

import com.application.petcare.entities.Pet;
import com.application.petcare.entities.Schedule;
import com.application.petcare.entities.Services;
import com.application.petcare.enums.StatusAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findAllByDeletedAtIsNull();

    Optional<List<Schedule>> findAllByIdInAndDeletedAtIsNull (List<Integer> ids);
    Optional<Schedule> findByScheduleDateAndScheduleTime(LocalDateTime scheduleDate, LocalTime scheduleTime);

    long countByScheduleStatus(StatusAgendamento status);

    List<Schedule> findByScheduleDateBetween(LocalDateTime start, LocalDateTime end);

    Schedule findTopByPetIdOrderByScheduleDateDesc(Integer petId);
    Integer countByPetId(Integer petId);



    List<Schedule> findByScheduleDateAndScheduleTimeBetweenAndServicesId(
            LocalDateTime scheduleDate,
            LocalTime startTime,
            LocalTime endTime,
            Integer serviceId
    );
}
