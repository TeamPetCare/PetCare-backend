package com.application.petcare.dto.especie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EspecieResponse {

    private UUID id;
    private String nomeEspecie;
    private Double preco;
}
