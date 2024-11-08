package com.application.petcare.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "Race")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String raceType;

    @Column(nullable = false)
    private Double price;
}