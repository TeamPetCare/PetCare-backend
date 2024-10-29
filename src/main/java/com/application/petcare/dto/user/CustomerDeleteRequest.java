package com.application.petcare.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDeleteRequest {
    
    private String cliente;
    private String endereco;
    private Integer id;
    private Integer numero_de_pets;
    private String whatsapp;

}
