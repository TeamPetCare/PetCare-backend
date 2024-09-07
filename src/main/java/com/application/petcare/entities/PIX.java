package com.application.petcare.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tb_pix")
public class PIX extends Pagamento {

    private Integer idPagamento;
    @Column(nullable = false)
    private String chavePix;

    public PIX(Integer id, Double valor, LocalDate dataPagamento, Integer idPagamento, String chavePix) {
        super(id, valor, dataPagamento);
        this.idPagamento = idPagamento;
        this.chavePix = chavePix;
    }

    @Override
    public Double calcularPagamento() {
        return getValor();
    }
}
