package com.application.petcare.dto.pagamento.debito;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DebitoCreateRequest {
    @NotBlank(message = "Valor is required")
    private Double valor;
    @NotBlank(message = "Data Pagamento is required")
    private LocalDate dataPagamento;
    @NotBlank(message = "Numero Cartão is required")
    private Integer numeroCartao;
    @NotBlank(message = "Nome Titular is required")
    private String nomeTitular;
    @NotBlank(message = "Validade is required")
    private LocalDate validade;
    @NotBlank(message = "Codigo De Segurança is required")
    private Integer codigoDeSeguranca;
}
