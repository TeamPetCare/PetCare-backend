package com.application.petcare.services.impl;

import com.application.petcare.strategy.FortnighPlanStrategy;
import com.application.petcare.strategy.MensalPlanStrategy;
import com.application.petcare.strategy.PlansStrategy;
import com.application.petcare.dto.plans.PlansCreateRequest;
import com.application.petcare.dto.plans.PlansResponse;
import com.application.petcare.entities.*;
import com.application.petcare.exceptions.ResourceNotFoundException;
import com.application.petcare.repository.*;
import com.application.petcare.services.PlansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class PlansServiceImpl implements PlansService {

    private PlansRepository plansRepository;

    private PlanTypeRepository planTypeRepository;
    private ServicesRepository servicesRepository;
    private PaymentModelRepository paymentModelRepository;
    private PetRepository petRepository;

    private final Map<Integer, PlansStrategy> mapStrategy = Map.of(
            15, new FortnighPlanStrategy(),
            30, new MensalPlanStrategy()
    );

    @Override
    public PlansResponse createPlans(PlansCreateRequest request) {
        Plans plans = Plans.builder()
                .subscriptionDate(request.getSubscriptionDate())
                .name(request.getName())
                .price(request.getPrice())
                .active(request.getActive())
                .renewal(request.getRenewal())
                .hasDiscount(request.getHasDiscount())
                .description(request.getDescription())
                .deletedAt(null)
                .planType(planTypeRepository.findById(request.getPlanTypeId())
                        .orElseThrow(() -> new ResourceNotFoundException("Plan Type not found")))
                .services(servicesRepository.findAllByIdInAndDeletedAtIsNull(request.getServicesIds()))
                .repeatQuantity(request.getRepeatQuantity())
                .payments(paymentModelRepository.findByIdInAndDeletedAtIsNull(request.getPaymentIds()))
                .pets(petRepository.findAllByIdInAndDeletedAtIsNull(request.getPetIds()))
                .build();
        Plans savedPlan = plansRepository.save(plans);
        return mapToResponse(savedPlan);
    }

    @Override
    public PlansResponse updatePlans(Integer id, PlansCreateRequest request) {
        Plans plans = plansRepository.findById(id)
                .orElseThrow(() -> new  ResourceNotFoundException("Plan not found"));

        plans.setSubscriptionDate(request.getSubscriptionDate());
        plans.setName(request.getName());
        plans.setPrice(request.getPrice());
        plans.setActive(request.getActive());
        plans.setRenewal(request.getRenewal());
        plans.setHasDiscount(request.getHasDiscount());
        plans.setDescription(request.getDescription());
        plans.setPlanType(planTypeRepository.findById(request.getPlanTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Plan type not found")));
        plans.setServices(servicesRepository.findAllByIdInAndDeletedAtIsNull(request.getServicesIds()));
        plans.setRepeatQuantity(request.getRepeatQuantity());
        plans.setPayments(paymentModelRepository.findByIdInAndDeletedAtIsNull(request.getPaymentIds()));
        plans.setPets(petRepository.findAllByIdInAndDeletedAtIsNull(request.getPetIds()));

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
        return plansRepository.findAllByDeletedAtIsNull().stream().map(this::mapToResponse).toList();
    }

    @Override
    public void deletePlans(Integer planId) {
        Plans plans = plansRepository.findById(planId)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));
        plans.setDeletedAt(LocalDateTime.now());
        plansRepository.save(plans);
    }

    @Override
    public Double applyDiscountInPlan(Integer planId) {
        Plans plans = plansRepository.findById(planId).orElseThrow(
                () -> new ResourceNotFoundException("Plan not found"));
        mapStrategy.get(plans.getPlanType().getPaymentInterval()).aplicarDesconto(plans);
        return plans.getPrice();
    }

    public PlansResponse mapToResponse(Plans plans){

        List<Integer> services = new ArrayList<>();
        for (int i = 0; i < plans.getServices().size(); i++) {
            services.add(plans.getServices().get(i).getId());
        }

        List<Integer> payments = new ArrayList<>();
        for (int i = 0; i < plans.getPayments().size(); i++) {
            payments.add(plans.getPayments().get(i).getId());
        }

        List<Integer> pets = new ArrayList<>();
        for (int i = 0; i < plans.getPets().size(); i++) {
            pets.add(plans.getPets().get(i).getId());
        }

        return PlansResponse.builder()
                .id(plans.getId())
                .subscriptionDate(plans.getSubscriptionDate())
                .name(plans.getName())
                .price(plans.getPrice())
                .active(plans.getActive())
                .renewal(plans.getRenewal())
                .hasDiscount(plans.getHasDiscount())
                .description(plans.getDescription())
                .planTypeId(plans.getPlanType().getId())
                .servicesIds(services)
                .repeatQuantity(plans.getRepeatQuantity())
                .paymentIds(payments)
                .petIds(pets)
                .build();
    }
}
