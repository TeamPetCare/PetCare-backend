package com.application.petcare.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Plans")
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime subscriptionDate;

    @NotNull
    private Double price;

    @NotNull
    private Boolean active;

    @NotNull
    private int renewal;

    @NotNull
    @ManyToOne
    private PlanType planType;



}
