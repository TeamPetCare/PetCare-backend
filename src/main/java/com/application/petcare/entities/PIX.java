package com.application.petcare.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class PIX extends Pagamento {

    @Column(nullable = false)
    private Integer idPagamento;
    @Column(nullable = false)
    private String chavePix;

    public PIX(Integer id, Double valor, LocalDate dataPagamento, Integer idPagamento, String chavePix) {
        super(id, valor, dataPagamento);
        this.idPagamento = idPagamento;
        this.chavePix = chavePix;
    }
}
