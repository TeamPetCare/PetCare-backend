package com.application.petcare.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Payment")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Plans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Date subscriptionDate;

    @NotNull
    private Double priece;

    @NotNull
    private Boolean active;

    @NotNull
    private int renewal;

    @NotNull
    @ManyToOne
    private PlanType planType;

    @NotNull
    @ManyToOne
    private Pet pet;


}
