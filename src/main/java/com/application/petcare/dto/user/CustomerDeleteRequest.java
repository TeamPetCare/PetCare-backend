package com.application.petcare.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDeleteRequest {
    
    private String cliente;
    private Integer numero;
    private String rua;
    private String bairro;
    private Integer id;
    private Integer numero_de_pets;
    private String whatsapp;

}
