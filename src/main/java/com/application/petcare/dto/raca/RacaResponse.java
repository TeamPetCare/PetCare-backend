package com.application.petcare.dto.raca;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RacaResponse {
    private Integer id;
    private String tipoRaca;
    private Double preco;
}