package com.application.petcare.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;
@Entity
@Table(name = "tb_pet")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pet {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    // muitos pets podem pertencer a um cliente
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    // Relacionamento com Especie (muitos pets podem ter uma espécie)
    @ManyToOne
    @JoinColumn(name = "especie_id", nullable = false)
    private Especie especie;

    // Relacionamento com Raca (muitos pets podem ter uma raça)
    @ManyToOne
    @JoinColumn(name = "raca_id", nullable = false)
    private Raca raca;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String sexo;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private double weight;

    // Relacionamento com Tamanho (Size)
    @ManyToOne
    @JoinColumn(name = "size_id", nullable = false)
    private Size size;

    @Column(name = "notes")
    private String notes;
}
