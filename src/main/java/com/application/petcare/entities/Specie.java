package com.application.petcare.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "Specie")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Specie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = true)
    private LocalDateTime deletedAt;
}