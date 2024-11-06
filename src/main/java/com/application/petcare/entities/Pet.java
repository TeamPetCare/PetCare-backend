package com.application.petcare.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Table(name = "Pet")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pet {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private double estimatedWeight;

    @Column(name = "birthdate", nullable = true)
    private LocalDate birthdate;

    @Column(nullable = false)
    private String petObservations;

    private String petImg;

    // Um pet um plano
    @ManyToOne
    private Plans plan;

    // Relacionamento com Especie (muitos pets podem ter uma espécie)
    @ManyToOne
    private Specie specie;

    // Relacionamento com Race (muitos pets podem ter uma raça)
    @ManyToOne
    private Race race;

    // Relacionamento com Tamanho (Size)
    @ManyToOne
    private Size size;
}
