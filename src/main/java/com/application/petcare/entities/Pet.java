package com.application.petcare.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Column(nullable = true)
    private LocalDateTime deletedAt;

    // Um pet um plano
    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = true)
    @JsonManagedReference
    private Plans plan;


    // Relacionamento com Especie (muitos pets podem ter uma espécie)
    @ManyToOne
    @JoinColumn(name = "specie_id", nullable = false)
    private Specie specie;

    // Relacionamento com Race (muitos pets podem ter uma raça)
    @ManyToOne
    @JoinColumn(name = "race_id", nullable = false)
    private Race race;

    // Relacionamento com Tamanho (Size)
    @ManyToOne
    @JoinColumn(name = "size_id", nullable = false)
    private Size size;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
