package com.application.petcare.dto.mercadopago;

import lombok.Data;

@Data
public class PixPaymentRequest {
    private Double amount;
    private String email;
    private String name;
    private String cpf;
}
