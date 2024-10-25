package com.application.petcare.dto.payment;

import com.application.petcare.entities.User;
import jakarta.persistence.Column;
import lombok.*;

import java.sql.Date;

@Getter
@Builder
@Setter
@Data
@AllArgsConstructor
public class PaymentResponse {
    private Integer id;
    private Double priece;
    private Date paymentDate;
    private Integer userId;
}
