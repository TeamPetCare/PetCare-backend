package com.application.petcare.controller;

import com.application.petcare.dto.plans.PlansCreateRequest;
import com.application.petcare.dto.plans.PlansResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Plans", description = "Gerenciar planos")
@RequestMapping("employee/api/plans")
public interface PlansController {
    @Operation(summary = "Criar um novo plano")
    @PostMapping
    ResponseEntity<PlansResponse> createPlan(@RequestBody PlansCreateRequest plansCreateRequest);

    @Operation(summary = "Buscar plano por ID")
    @GetMapping("/{id}")
    ResponseEntity<PlansResponse> getPlanById(@PathVariable Integer id);

    @Operation(summary = "Buscar todos os planos")
    @GetMapping
    ResponseEntity<List<PlansResponse>> getAllPlan();

    @Operation(summary = "Atualiza plano por ID")
    @PutMapping("/{id}")
    ResponseEntity<PlansResponse> updatePlan(
            @PathVariable Integer id,
            @RequestBody PlansCreateRequest plansCreateRequest);

    @Operation(summary = "Excluir plano por ID")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePlan(@PathVariable Integer id);

    @Operation(summary = "Aplicar desconto em plano por ID")
    @GetMapping("/apply-discount/{id}")
    ResponseEntity<Double> applyDiscountInPlan(@PathVariable Integer id);
}
