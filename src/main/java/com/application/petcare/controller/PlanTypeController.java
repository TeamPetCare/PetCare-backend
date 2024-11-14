package com.application.petcare.controller;

import com.application.petcare.dto.plantype.PlanTypeCreateRequest;
import com.application.petcare.dto.plantype.PlanTypeResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Plan Type", description = "Gerenciar tipos de planos")
@RequestMapping("employee/api/plan-types")
public interface PlanTypeController {

    @Operation(summary = "Criar um novo tipo plano")
    @PostMapping
    ResponseEntity<PlanTypeResponse> createPlanType(@RequestBody PlanTypeCreateRequest planTypeCreateRequest);

    @Operation(summary = "Buscar tipo plano por ID")
    @GetMapping("/{id}")
    ResponseEntity<PlanTypeResponse> getPlanTypeById(@PathVariable Integer id);

    @Operation(summary = "Buscar todos os tipos de planos")
    @GetMapping
    ResponseEntity<List<PlanTypeResponse>> getAllPlanTypes();

    @Operation(summary = "Atualizar tipo de plano por ID")
    @PutMapping("/{id}")
    ResponseEntity<PlanTypeResponse> updatePlanType(
            @PathVariable Integer id,
            @RequestBody PlanTypeCreateRequest planTypeCreateRequest);

    @Operation(summary = "Excluir tipo plano por ID")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePlanType(@PathVariable Integer id);

}
