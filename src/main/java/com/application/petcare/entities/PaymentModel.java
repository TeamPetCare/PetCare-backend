package com.application.petcare.entities;

import com.application.petcare.enums.PaymentMethod;
import com.application.petcare.enums.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Payment" )
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PaymentModel {

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

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @NotNull
    @ManyToOne
    private User user;

    @Column(length = 5000)
    private String qrCodeImageBase64;

    private String paymentLink;

    private LocalDateTime deletedAt;
}
