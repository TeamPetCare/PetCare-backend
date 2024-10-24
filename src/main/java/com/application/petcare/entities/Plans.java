package com.application.petcare.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    //Fazer relacionamento
    private PlanType planType;
    @NotNull
    //Fazer relacionamento
    private Pet pet;


}
