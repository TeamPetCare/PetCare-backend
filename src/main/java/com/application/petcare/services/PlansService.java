package com.application.petcare.services;

import com.application.petcare.dto.plans.PlansCreateRequest;
import com.application.petcare.dto.plans.PlansResponse;
import com.application.petcare.dto.plans.UserPlansResponse;

import java.util.List;

public interface PlansService {
    PlansResponse createPlans(PlansCreateRequest request);

    PlansResponse updatePlans(Integer id, PlansCreateRequest request);

    PlansResponse findPlansById(Integer planId);

    List<UserPlansResponse> findAllPlansByUserId(Integer userId);

    List<PlansResponse> findAllPlans();

    void deletePlans(Integer planId);

    Double applyDiscountInPlan(Integer planId);
}
