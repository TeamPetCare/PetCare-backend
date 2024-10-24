package com.application.petcare.dto.payment;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class PaymentCreateRequest {
    @NotNull(message = "Priece is required")
    private Double priece;
    @NotNull(message = "Payment date is required")
    private Date paymentDate;
    @NotNull(message = "User id is required")
    private Integer userId;
}
