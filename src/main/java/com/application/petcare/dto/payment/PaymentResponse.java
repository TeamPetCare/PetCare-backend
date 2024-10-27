package com.application.petcare.dto.payment;

import com.application.petcare.entities.User;
import jakarta.persistence.Column;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Builder
@Setter
@Data
@AllArgsConstructor
public class PaymentResponse {
    private Integer id;
    private Double priece;
    private LocalDateTime paymentDate;
    private Integer userId;
}
