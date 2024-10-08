package com.application.petcare.services;

import com.application.petcare.config.MercadoPagoConfiguration;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.resources.payment.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PixPaymentService {

    @Autowired
    private MercadoPagoConfiguration mercadoPagoConfiguration;

    public Payment createPixPayment(Double amount, String email, String name, String cpf) {
        mercadoPagoConfiguration.configure();

        Map<String, String> customHeaders = new HashMap<>();
        customHeaders.put("x-idempotency-key", UUID.randomUUID().toString());

        MPRequestOptions requestOptions = MPRequestOptions.builder()
                .customHeaders(customHeaders)
                .build();

        PaymentClient client = new PaymentClient();

        PaymentCreateRequest createRequest = PaymentCreateRequest.builder()
                .transactionAmount(BigDecimal.valueOf(amount))
                .description("Agendamento Petshop")
                .paymentMethodId("pix")
                .payer(PaymentPayerRequest.builder()
                        .email(email)
                        .firstName(name)
                        .identification(IdentificationRequest.builder()
                                .type("CPF")
                                .number(cpf)
                                .build())
                        .build())
                .build();
        try {
            Payment payment = client.create(createRequest, requestOptions);
            System.out.println("Payment created successfully: " + payment);
            return payment;
        } catch (MPApiException e) {
            System.err.println("API error: " + e.getMessage());
            System.err.println("Error details: " + e.getMessage());
            throw new RuntimeException("Error to create payment: " + e.getMessage());
        } catch (Exception ex) {
            throw new RuntimeException("Error to create order", ex);
        }

    }
}