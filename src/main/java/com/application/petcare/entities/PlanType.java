package com.application.petcare.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "PlanType")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Integer paymentInterval;
    @Column(nullable = true)
    private LocalDateTime deletedAt;

}
