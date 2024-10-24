package com.application.petcare.controller.impl;

import com.application.petcare.controller.PlansController;
import com.application.petcare.dto.plans.PlansCreateRequest;
import com.application.petcare.dto.plans.PlansResponse;
import com.application.petcare.services.PlansService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RequiredArgsConstructor
public class PlansControllerImpl implements PlansController {

    private PlansService plansService;

    @Override
    public ResponseEntity<PlansResponse> createPlan(PlansCreateRequest plansCreateRequest) {
        return ResponseEntity.status(201).body(plansService.createPlans(plansCreateRequest));
    }

    @Override
    public ResponseEntity<PlansResponse> getPlanById(Integer id) {
        return ResponseEntity.ok().body(plansService.findPlansById(id));
    }

    @Override
    public ResponseEntity<List<PlansResponse>> getAllPlan() {
        return ResponseEntity.ok().body(plansService.findAllPlans());
    }

    @Override
    public ResponseEntity<PlansResponse> updatePlan(Integer id, PlansCreateRequest plansCreateRequest) {
        return ResponseEntity.ok().body(plansService.updatePlans(id, plansCreateRequest));
    }

    @Override
    public ResponseEntity<Void> deletePlan(Integer id) {
        plansService.deletePlans(id);
        return ResponseEntity.noContent().build();
    }
}
