package com.application.petcare.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "PlanType")
@Builder
@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class PlanType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer paymentInterval;

}
