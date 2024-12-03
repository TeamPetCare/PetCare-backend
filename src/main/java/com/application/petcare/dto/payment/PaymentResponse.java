package com.application.petcare.dto.payment;

import com.application.petcare.entities.User;
import com.application.petcare.enums.PaymentMethod;
import com.application.petcare.enums.PaymentStatus;
import com.application.petcare.enums.StatusAgendamento;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@Setter
@Data
@AllArgsConstructor
public class PaymentResponse {
    private Integer id;
    private Double price;
    private LocalDateTime paymentDate;
    private String paymentId;
    private PaymentStatus paymentStatus;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private Integer userId;
}
