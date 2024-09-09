package com.application.petcare.dto.pagamento.debito;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DebitoResponse {
    private Integer id;
    private Double valor;
    private LocalDate dataPagamento;
    private Integer idPagamento;
    private Integer numeroCartao;
    private String nomeTitular;
    private LocalDate validade;
    private Integer codigoDeSeguranca;
}
