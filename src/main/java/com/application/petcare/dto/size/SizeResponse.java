package com.application.petcare.dto.size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SizeResponse {

    private Integer id;
    private String tipoTamanho;
    private BigDecimal preco;
}
