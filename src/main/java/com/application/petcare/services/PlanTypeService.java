package com.application.petcare.services;


import com.application.petcare.dto.plantype.PlanTypeCreateRequest;
import com.application.petcare.dto.plantype.PlanTypeResponse;

import java.util.List;

public interface PlanTypeService {
    PlanTypeResponse createPlanType(PlanTypeCreateRequest request);

    PlanTypeResponse updatePlanType(Integer id, PlanTypeCreateRequest request);

    PlanTypeResponse findPlanTypeById(Integer userId);

    List<PlanTypeResponse> findAllPlanTypes();

    void deletePlanType(Integer userId);
}
