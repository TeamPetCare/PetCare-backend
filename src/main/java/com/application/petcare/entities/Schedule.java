package com.application.petcare.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Time;
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
    private String scheduleStatus;

    @NotNull
    private Date scheduleDate;

    @NotNull
    private Time scheduleTime;

    @NotNull
    private Date creationDate;

    @NotBlank
    private String scheduleNote;

    @NotNull
    @OneToMany
    private List<Pet> pet;

    @NotNull
    @OneToOne
    private Payment payment;


}
