package com.application.petcare.enums;

public enum Tamanho {
    PEQUENO(50.0),
    MEDIO(70.0),
    GRANDE(100.0);

    private final double preco;

    Tamanho(double preco) {
        this.preco = preco;
    }

    public double getPreco() {
        return preco;
    }
}
