package com.application.petcare.dto.raca;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RacaResponse {
    private UUID id;
    private String tipoRaca;
    private Double preco;
}