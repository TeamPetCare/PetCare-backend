package com.application.petcare.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private Boolean disponibility;
    private Double price;
    private String description;
    private Integer paymentInterval;
    @Column(nullable = true)
    private LocalDateTime deletedAt;

    @NotNull
    @ManyToMany
    @JoinTable(
            name = "plan_type_services",
            joinColumns = @JoinColumn(name = "plan_type_id"),
            inverseJoinColumns = @JoinColumn(name = "services_id")
    )
    private List<Services> services = new ArrayList<>();

}
