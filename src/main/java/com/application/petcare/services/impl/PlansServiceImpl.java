package com.application.petcare.services.impl;

import com.application.petcare.dto.plans.PlansCreateRequest;
import com.application.petcare.dto.plans.PlansResponse;
import com.application.petcare.entities.*;
import com.application.petcare.exceptions.ResourceNotFoundException;
import com.application.petcare.repository.*;
import com.application.petcare.services.PlansService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class PlansServiceImpl implements PlansService {


    private PlansRepository plansRepository;

    private PetRepository petRepository;
    private PlanTypeRepository planTypeRepository;

    @Override
    public PlansResponse createPlans(PlansCreateRequest request) {
        Plans plans = Plans.builder()
                .subscriptionDate(request.getSubscriptionDate())
                .price(request.getPrice())
                .active(request.getActive())
                .renewal(request.getRenewal())
                .planType(planTypeRepository.findById(request.getPlanTypeId())
                        .orElseThrow(() -> new ResourceNotFoundException("Plan Type not found")))
                .build();
        Plans savedPlan = plansRepository.save(plans);
        return mapToResponse(savedPlan);
    }

    @Override
    public PlansResponse updatePlans(Integer id, PlansCreateRequest request) {
        Plans plans = plansRepository.findById(id)
                .orElseThrow(() -> new  ResourceNotFoundException("Plan not found"));

        plans.setSubscriptionDate(request.getSubscriptionDate());
        plans.setPrice(request.getPrice());
        plans.setActive(request.getActive());
        plans.setRenewal(request.getRenewal());
        plans.setPlanType(planTypeRepository.findById(request.getPlanTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Plan type not found")));

        Plans updatedPlan = plansRepository.save(plans);
        return mapToResponse(updatedPlan);
    }

    @Override
    public PlansResponse findPlansById(Integer planId) {
        Plans plan = plansRepository.findById(planId)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));
        return mapToResponse(plan);
    }

    @Override
    public List<PlansResponse> findAllPlans() {
        return plansRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    @Override
    public void deletePlans(Integer planId) {
        Plans plans = plansRepository.findById(planId)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));
        plansRepository.deleteById(planId);
    }

    public PlansResponse mapToResponse(Plans plans){
        return PlansResponse.builder()
                .id(plans.getId())
                .subscriptionDate(plans.getSubscriptionDate())
                .price(plans.getPrice())
                .active(plans.getActive())
                .renewal(plans.getRenewal())
                .planTypeId(plans.getPlanType().getId())
                .build();
    }
}
