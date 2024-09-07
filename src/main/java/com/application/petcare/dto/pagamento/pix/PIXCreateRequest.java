package com.application.petcare.dto.pagamento.pix;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PIXCreateRequest {
    @NotBlank(message = "Valor is required")
    private Double valor;
    @NotBlank(message = "Data Pagamento is required")
    private LocalDate dataPagamento;
    @NotBlank(message = "Chave PIX is required")
    private String chavePix;
}
