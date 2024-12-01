package com.application.petcare.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Column(nullable = true)
    private Boolean hasDiscount;

    @NotNull
    @ManyToOne
    private PlanType planType;

    // resolvendo o problema
    @NotNull
    @ManyToMany
    @JsonManagedReference
    @JoinTable(
            name = "plan_services",
            joinColumns = @JoinColumn(name = "plan_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<Services> services = new ArrayList<>();

    private List<Integer> repeatQuantity = new ArrayList<>();

    @Column(nullable = true)
    private LocalDateTime deletedAt;

    @NotNull
    @OneToMany
    private List<Payment> payments;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Pet> pets = new ArrayList<>();

}
