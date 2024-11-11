package com.application.petcare.strategy;

import com.application.petcare.entities.Plans;

public class FortnighPlanStrategy implements PlansStrategy{
    @Override
    public void aplicarDesconto(Plans plan) {
        plan.setPrice(plan.getPrice() * 0.95);
    }
}
