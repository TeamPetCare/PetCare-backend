package com.application.petcare.services.impl;

import com.application.petcare.dto.plantype.PlanTypeCreateRequest;
import com.application.petcare.dto.plantype.PlanTypeResponse;
import com.application.petcare.entities.PlanType;
import com.application.petcare.exceptions.ResourceNotFoundException;
import com.application.petcare.repository.PlanTypeRepository;
import com.application.petcare.services.PlanTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlanTypeServiceImpl implements PlanTypeService {

    private final PlanTypeRepository repository;

    @Override
    public PlanTypeResponse createPlanType(PlanTypeCreateRequest request) {
        PlanType planType = PlanType.builder()
                .name(request.getName())
                .paymentInterval(request.getPaymentInterval()).build();
        PlanType savedPlanType = repository.save(planType);
        return mapToResponse(savedPlanType);
    }

    @Override
    public PlanTypeResponse updatePlanType(Integer id, PlanTypeCreateRequest request) {
        PlanType planType = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan Type not found with id:" + id));

        planType.setName(request.getName());
        planType.setPaymentInterval(planType.getPaymentInterval());

        PlanType updatedPlanType = repository.save(planType);
        return mapToResponse(updatedPlanType);
    }

    @Override
    public PlanTypeResponse findPlanTypeById(Integer planTypeId) {
        PlanType planType = repository.findById(planTypeId)
            .orElseThrow(() -> new ResourceNotFoundException("Plan Type not found with id: " + planTypeId));
        return mapToResponse(planType);
    }

    @Override
    public List<PlanTypeResponse> findAllPlanTypes() {
        return repository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePlanType(Integer planTypeId) {
        if (!repository.existsById(planTypeId)) {
            throw new ResourceNotFoundException("User not found with ID: " + planTypeId);
        }
        repository.deleteById(planTypeId);
    }

    public PlanTypeResponse mapToResponse(PlanType request){
        return PlanTypeResponse.builder()
                .name(request.getName())
                .paymentInterval(request.getPaymentInterval()).build();
    }
}
