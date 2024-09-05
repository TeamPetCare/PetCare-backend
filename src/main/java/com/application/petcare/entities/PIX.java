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
public abstract class PIX extends Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    private Integer idPagamento;
    @Column(nullable = false)
    private String chavePix;

    public PIX(Integer id, Double valor, LocalDate dataPagamento, Integer idPagamento, String chavePix) {
        super(id, valor, dataPagamento);
        this.idPagamento = idPagamento;
        this.chavePix = chavePix;
    }
}
