package com.application.petcare.entities;

import com.application.petcare.enums.StatusAgendamento;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Schedule")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private StatusAgendamento scheduleStatus;

    @NotNull
    private LocalDateTime scheduleDate;

    @NotNull
    private LocalTime scheduleTime;

    @NotNull
    private LocalDateTime creationDate;

    @NotBlank
    private String scheduleNote;

    @NotNull
    @OneToOne
    private Pet pet;

    @NotNull
    @OneToOne
    private Payment payment;

    @ManyToMany
    @JsonBackReference
    @JoinTable(
            name = "Schedule_has_Services",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "services_id")
    )
    private List<Services> services;

    }


