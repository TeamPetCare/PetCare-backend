package com.application.petcare.controller;

import com.application.petcare.dto.pet.PetCreateRequest;
import com.application.petcare.dto.pet.PetResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Pets", description = "Gerenciamento de pets")
@RequestMapping("/api/pets")
public interface PetController {

    @Operation(summary = "Criar um novo pet")
    @PostMapping
    ResponseEntity<PetResponse> createPet(@RequestBody PetCreateRequest request);

    @Operation(summary = "Atualizar um pet existente")
    @PutMapping("/{id}")
    ResponseEntity<PetResponse> updatePet(@PathVariable UUID id, @RequestBody PetCreateRequest request);

    @Operation(summary = "Buscar um pet pelo ID")
    @GetMapping("/{id}")
    ResponseEntity<PetResponse> getPetById(@PathVariable UUID id);

    @Operation(summary = "Listar todos os pets")
    @GetMapping
    ResponseEntity<List<PetResponse>> getAllPets();

    @Operation(summary = "Deletar um pet")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePet(@PathVariable UUID id);
}
