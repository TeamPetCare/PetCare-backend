package com.application.petcare.dto.raca;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RacaCreateRequest {

    @NotBlank(message = "Tipo da raça é obrigatório")
    private String tipoRaca;

    @NotNull(message = "Preço é obrigatório")
    private Double preco;
}