package com.application.petcare.dto.mercadopago;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentGetResponse {
    private String status;
    @JsonProperty("external_reference")
    private String externalReference;
}
