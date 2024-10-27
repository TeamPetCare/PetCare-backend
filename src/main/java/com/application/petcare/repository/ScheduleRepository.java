package com.application.petcare.repository;

import com.application.petcare.entities.Schedule;
import com.application.petcare.enums.StatusAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    Optional<List<Schedule>> findAllByIdIn (List<Integer> ids);
    Optional<Schedule> findByScheduleDateAndScheduleTime(LocalDateTime scheduleDate, LocalTime scheduleTime);

    long countByScheduleStatus(StatusAgendamento status);

}
