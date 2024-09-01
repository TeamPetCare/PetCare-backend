package com.application.petcare.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class Pagamento {

    @Column(nullable = false)
    private Integer id;
    @Column(nullable = false)
    private Double valor;
    private LocalDate dataPagamento;

    public Pagamento(Integer id, Double valor, LocalDate dataPagamento) {
        this.id = id;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
    }

    public abstract Double calcularPagamento();
}
