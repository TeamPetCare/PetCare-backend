package com.application.petcare.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @NotBlank
    private String name;

    @NotNull
    private Double price;

    @NotNull
    private Boolean active;

    @NotNull
    private int renewal;

    @NotBlank
    private String description;

    @NotNull
    @ManyToOne
    private PlanType planType;

    @NotNull
    @OneToMany
    private List<Services> services = new ArrayList<>();

    private List<Integer> repeatQuantity = new ArrayList<>();

    @NotNull
    @OneToMany
    private List<Payment> payments;

}
