package com.application.petcare.dto.payment;

import com.application.petcare.enums.PaymentMethod;
import com.application.petcare.enums.PaymentStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@Setter
@Data
@AllArgsConstructor
public class PaymentCreateRequest {
    @NotNull(message = "Price is required")
    private Double price;
    @NotNull(message = "Payment date is required")
    private LocalDateTime paymentDate;
    @NotNull(message = "Payment ID is required")
    private String paymentId;
    @NotNull(message = "Payment Method is required")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @NotNull(message = "Payment Status is required")
    private PaymentStatus paymentStatus;
    @NotNull(message = "User id is required")
    private Integer userId;
}
