package com.application.petcare.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Table(name = "Pet")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    // muitos pets podem pertencer a um cliente
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User user;

    // Relacionamento com Especie (muitos pets podem ter uma espécie)
    @ManyToOne
    @JoinColumn(name = "especie_id", nullable = false)
    private Specie specie;

    // Relacionamento com Raca (muitos pets podem ter uma raça)
    @ManyToOne
    @JoinColumn(name = "raca_id", nullable = false)
    private Race race;

    // Relacionamento com Tamanho (Size)
    @ManyToOne
    @JoinColumn(name = "size_id", nullable = false)
    private Size size;
}
