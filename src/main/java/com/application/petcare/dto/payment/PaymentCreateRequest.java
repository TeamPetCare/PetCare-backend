package com.application.petcare.dto.payment;

import com.application.petcare.entities.User;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

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
    private String paymentMethod;
    @NotNull(message = "Payment Status is required")
    private String paymentStatus;
    @NotNull(message = "User id is required")
    private Integer userId;
}
