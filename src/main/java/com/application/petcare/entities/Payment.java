package com.application.petcare.entities;

import com.application.petcare.enums.PaymentMethod;
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

    @NotNull
    @ManyToOne
    private User user;

}
