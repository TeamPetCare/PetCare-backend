package com.application.petcare.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "tb_raca")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Raca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String tipoRaca;

    @Column(nullable = false)
    private Double preco;
}