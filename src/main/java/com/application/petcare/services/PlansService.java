package com.application.petcare.services;

import com.application.petcare.dto.plans.PlansCreateRequest;
import com.application.petcare.dto.plans.PlansResponse;

import java.util.List;

public interface PlansService {
    PlansResponse createPlans(PlansCreateRequest request);

    PlansResponse updatePlans(Integer id, PlansCreateRequest request);

    PlansResponse findPlansById(Integer planId);

    List<PlansResponse> findAllPlans();

    void deletePlans(Integer planId);

    Double applyDiscountInPlan(Integer planId);
}
