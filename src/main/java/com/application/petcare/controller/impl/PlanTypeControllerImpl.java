package com.application.petcare.controller.impl;

import com.application.petcare.controller.PlanTypeController;
import com.application.petcare.dto.plantype.PlanTypeCreateRequest;
import com.application.petcare.dto.plantype.PlanTypeResponse;
import com.application.petcare.services.PlanTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlanTypeControllerImpl implements PlanTypeController {

    private final PlanTypeService service;

    @PostMapping
    public ResponseEntity<PlanTypeResponse> createPlanType(PlanTypeCreateRequest planTypeCreateRequest) {
        return ResponseEntity.status(201).body(service.createPlanType(planTypeCreateRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanTypeResponse> getPlanTypeById(Integer id) {
        return ResponseEntity.ok(service.findPlanTypeById(id));
    }

    @GetMapping
    public ResponseEntity<List<PlanTypeResponse>> getAllPlanTypes() {
        return ResponseEntity.ok(service.findAllPlanTypes());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanTypeResponse> updatePlanType(Integer id, PlanTypeCreateRequest planTypeCreateRequest) {
        return ResponseEntity.ok(service.updatePlanType(id, planTypeCreateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanType(Integer id) {
        service.deletePlanType(id);
        return ResponseEntity.noContent().build();
    }
}
