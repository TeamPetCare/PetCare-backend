package com.application.petcare.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@Entity
public abstract class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
