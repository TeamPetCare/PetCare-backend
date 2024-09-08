package com.application.petcare.dto.especie;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EspecieCreateRequest {

    @NotBlank(message = "O nome da espécie é obrigatório")
    private String nomeEspecie;

    @NotNull(message = "O preço é obrigatório")
    private Double preco;
}