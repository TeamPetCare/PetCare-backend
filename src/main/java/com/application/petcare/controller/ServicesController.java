package com.application.petcare.controller;

import com.application.petcare.dto.services.ServicesCreateRequest;
import com.application.petcare.dto.services.ServicesResponse;
import com.application.petcare.dto.services.ServicesUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Services", description = "Gerenciar funcionários")
@RequestMapping("/api/services")
public interface ServicesController {
    @Operation(summary = "Criar um novo serviço")
    @PostMapping
    ResponseEntity<ServicesResponse> createService(@RequestBody ServicesCreateRequest servicesCreateRequest);

    @Operation(summary = "Buscar serviços por ID")
    @GetMapping("/{id}")
    ResponseEntity<ServicesResponse> getServiceById(@PathVariable Integer id);

    @Operation(summary = "Buscar todos os serviços")
    @GetMapping
    ResponseEntity<List<ServicesResponse>> getAllServices();

    @Operation(summary = "Atualizar serviço por ID")
    @PutMapping("/{id}")
    ResponseEntity<ServicesResponse> updateService(
            @PathVariable Integer id,
            @RequestBody ServicesUpdateRequest servicesCreateRequest);

    @Operation(summary = "Excluir serviço por ID")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteService(@PathVariable Integer id);

    @Operation(summary = "Busca serviços por uma lista de IDs")
    @GetMapping("/ids-list/{ids}")
    ResponseEntity<List<ServicesResponse>> getServicesByIdsList(@RequestParam List<Integer> ids);
}
