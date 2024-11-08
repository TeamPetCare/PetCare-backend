package com.application.petcare.dto.size;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SizeCreateRequest {

    @NotBlank(message = "Tipo Tamanho é obrigatório")
    private String sizeType;

    @NotNull(message = "Preco é obrigatório")
    private BigDecimal price;
}
