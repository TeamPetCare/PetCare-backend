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
public abstract class Credito extends Pagamento {

    @Column(nullable = false)
    private Integer idPagamento;
    @Column(nullable = false)
    private Integer numeroCartao;
    @Column(nullable = false)
    private String nomeTitular;
    @Column(nullable = false)
    private LocalDate validade;
    @Column(nullable = false)
    private Integer codigoDeSeguranca;

    public Credito(Integer id, Double valor, LocalDate dataPagamento, Integer idPagamento, Integer numeroCartao, String nomeTitular, LocalDate validade, Integer codigoDeSeguranca) {
        super(id, valor, dataPagamento);
        this.idPagamento = idPagamento;
        this.numeroCartao = numeroCartao;
        this.nomeTitular = nomeTitular;
        this.validade = validade;
        this.codigoDeSeguranca = codigoDeSeguranca;
    }
}
