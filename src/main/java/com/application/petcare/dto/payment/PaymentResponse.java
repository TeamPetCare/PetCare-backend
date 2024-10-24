package com.application.petcare.dto.payment;

import com.application.petcare.entities.User;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class PaymentResponse {
    private Integer id;
    private Double priece;
    private Date paymentDate;
    private Integer userId;
}
