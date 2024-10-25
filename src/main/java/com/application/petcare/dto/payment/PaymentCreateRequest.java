package com.application.petcare.dto.payment;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Date;

@Getter
@Builder
@Setter
@Data
@AllArgsConstructor
public class PaymentCreateRequest {
    @NotNull(message = "Priece is required")
    private Double priece;
    @NotNull(message = "Payment date is required")
    private Date paymentDate;
    @NotNull(message = "User id is required")
    private Integer userId;
}
