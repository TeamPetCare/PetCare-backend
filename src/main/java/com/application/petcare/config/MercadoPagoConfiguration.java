package com.application.petcare.config;

import com.mercadopago.MercadoPagoConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MercadoPagoConfiguration {

    @PostConstruct
    public void init() {
        configure();
    }


    @Value("${app.mercadopago.secret.apiKey}")
    private String apiKey;

    public void configure() {
        MercadoPagoConfig.setAccessToken(apiKey);
    }
}
