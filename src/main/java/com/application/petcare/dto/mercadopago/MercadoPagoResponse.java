package com.application.petcare.dto.mercadopago;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MercadoPagoResponse {
    private String action;
    private MercadoPagoData data;
    @JsonProperty("external_reference")
    private String externalReference;
}
