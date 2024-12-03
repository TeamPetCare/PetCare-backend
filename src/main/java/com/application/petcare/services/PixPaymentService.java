package com.application.petcare.services;

import com.application.petcare.dto.mercadopago.MercadoPagoResponse;
import com.application.petcare.entities.PaymentModel;
import com.application.petcare.entities.User;

public interface PixPaymentService {
    void checkPayment(MercadoPagoResponse mercadoPagoResponse);
    PaymentModel createPixPayment(Double amount, User user);
}
