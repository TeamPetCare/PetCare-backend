package com.application.petcare.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tb_raca")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Raca {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String tipoRaca;

    @Column(nullable = false)
    private Double preco;
}