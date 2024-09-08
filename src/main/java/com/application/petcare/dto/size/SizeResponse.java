package com.application.petcare.dto.size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SizeResponse {

    private UUID id;
    private String tipoTamanho;
    private BigDecimal preco;
}
