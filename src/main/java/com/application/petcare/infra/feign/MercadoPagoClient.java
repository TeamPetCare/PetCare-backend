package com.application.petcare.infra.feign;

import com.application.petcare.dto.mercadopago.PaymentGetResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "mercadoPagoClient", url = "https://api.mercadopago.com/v1")
public interface MercadoPagoClient {

    @GetMapping("/payments/{paymentId}")
    PaymentGetResponse checkPaymentStatus(
            @PathVariable("paymentId") String paymentId,
            @RequestHeader("Authorization") String bearerToken);
}