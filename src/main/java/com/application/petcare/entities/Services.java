package com.application.petcare.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "Services")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String note;

    @Column(nullable = false)
    private Double priece;

    @Column(nullable = false)
    private Time estimatedTime;

    @Column(nullable = false)
    private Boolean disponibility;

    @ManyToMany
    @JsonManagedReference
    private List<Schedule> schedules;

}