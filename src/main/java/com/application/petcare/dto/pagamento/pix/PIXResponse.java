package com.application.petcare.dto.pagamento.pix;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PIXResponse {
    private Integer id;
    private Double valor;
    private LocalDate dataPagamento;
    private Integer idPagamento;
    private String chavePix;
}
