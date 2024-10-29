package com.application.petcare.designPattern;

public class FortnightPlanType implements PlanTypeStrategy {

    @Override
    public double aplicarDesconto(double preco) {
        return preco * 0.95;
    }
}
