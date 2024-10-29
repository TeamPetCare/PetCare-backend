package com.application.petcare.designPattern;

public class MensalPlanType implements PlanTypeStrategy {

    @Override
    public double aplicarDesconto(double preco) {
        return preco * 0.9;
    }
}
