package com.application.petcare.services.impl;

import com.application.petcare.config.MercadoPagoConfiguration;
import com.application.petcare.dto.mercadopago.MercadoPagoResponse;
import com.application.petcare.dto.mercadopago.PaymentGetResponse;
import com.application.petcare.entities.PaymentModel;
import com.application.petcare.entities.User;
import com.application.petcare.enums.PaymentMethod;
import com.application.petcare.enums.PaymentStatus;
import com.application.petcare.infra.feign.MercadoPagoClient;
import com.application.petcare.repository.PaymentModelRepository;
import com.application.petcare.services.PixPaymentService;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.resources.payment.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PixPaymentServiceImpl implements PixPaymentService {

    private final MercadoPagoConfiguration mercadoPagoConfiguration;
    private final PaymentModelRepository paymentModelRepository;
    private final MercadoPagoClient mercadoPagoClient;

    @Value("${app.mercadopago.secret.apiKey}")
    private String apiKey;

    @Override
    public void checkPayment(MercadoPagoResponse mercadoPagoResponse) {

        PaymentGetResponse paymentGetResponse =
                mercadoPagoClient.checkPaymentStatus(
                        mercadoPagoResponse.getData().getId(),
                        "Bearer " + apiKey);

        PaymentModel paymentModel =
                paymentModelRepository
                        .findByPaymentId(paymentGetResponse.getExternalReference())
                        .orElseThrow(() -> new RuntimeException("Payment not found"));


        if(paymentGetResponse.getStatus().equals("pending")) {
            paymentModel.setPaymentStatus(PaymentStatus.PENDING);
            paymentModelRepository.save(paymentModel);
        }

        if(paymentGetResponse.getStatus().equals("approved")){

            if (paymentModel.getPaymentStatus().equals(PaymentStatus.APPROVED)) {
                throw new RuntimeException("This payment is already approved");
            }

            paymentModel.setPaymentStatus(PaymentStatus.APPROVED);
            paymentModelRepository.save(paymentModel);
        }

        if(paymentGetResponse.getStatus().equals("rejected") || paymentGetResponse.getStatus().equals("cancelled")) {
            paymentModel.setPaymentStatus(PaymentStatus.CANCELLED);
            paymentModelRepository.save(paymentModel);
        }

    }

    @Override
    public PaymentModel createPixPayment(Double amount, User user) {
        mercadoPagoConfiguration.configure();

        Map<String, String> customHeaders = new HashMap<>();
        customHeaders.put("X-idempotency-key", UUID.randomUUID().toString());

        MPRequestOptions requestOptions = MPRequestOptions.builder()
                .customHeaders(customHeaders)
                .build();

        PaymentClient client = new PaymentClient();
        OffsetDateTime now = OffsetDateTime.now();

        PaymentCreateRequest createRequest = PaymentCreateRequest.builder()
                .transactionAmount(BigDecimal.valueOf(amount))
                .description("Agendamento Petshop")
                .paymentMethodId("pix") //Fixo em pix, existem formas de implementar servico de credito e debito porem necessita alterar a logica de payment
                .dateOfExpiration(now.plusMinutes(30))
                .callbackUrl("https://petcare-web-deb8bbbjcqgecucg.eastus-01.azurewebsites.net/")
                .externalReference(UUID.randomUUID().toString())
                .payer(PaymentPayerRequest.builder()
                        .email(user.getEmail())
                        .firstName(user.getName())
                        .identification(IdentificationRequest.builder()
                                .type("CPF")
                                .number(user.getCpfClient())
                                .build())
                        .build())
                .build();
        try {
            Payment payment = client.create(createRequest, requestOptions);
            System.out.println("Payment created successfully: " + payment);

            PaymentModel createdPayment = new PaymentModel();
            createdPayment.setPaymentLink(payment.getPointOfInteraction().getTransactionData().getTicketUrl());
            createdPayment.setQrCodeImageBase64(payment.getPointOfInteraction().getTransactionData().getQrCodeBase64());
            createdPayment.setPaymentDate(LocalDateTime.from(payment.getDateCreated()));
            createdPayment.setPrice(payment.getTransactionAmount().doubleValue());
            createdPayment.setPaymentId(payment.getExternalReference());
            createdPayment.setPaymentMethod(PaymentMethod.PIX);
            createdPayment.setPaymentStatus(PaymentStatus.PENDING);
            createdPayment.setUser(user);

            return paymentModelRepository.save(createdPayment);
        } catch (MPApiException e) {
            System.err.println("API error: " + e.getMessage());
            System.err.println("Error details: " + e.getMessage());
            throw new RuntimeException("Error to create payment: " + e.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException("Error to create order", ex);
        }
    }
}