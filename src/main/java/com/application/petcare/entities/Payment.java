package com.application.petcare.entities;

import com.application.petcare.enums.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Payment")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Payment {

    //Verificar a criação do conotroller

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private LocalDateTime paymentDate;

    private String paymentId;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private Boolean paymentStatus;


    @Column(nullable = true)
    private LocalDateTime deletedAt;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

}
