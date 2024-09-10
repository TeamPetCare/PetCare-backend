package com.application.petcare.controller;

import com.application.petcare.dto.pet.PetCreateRequest;
import com.application.petcare.dto.pet.PetResponse;
import com.application.petcare.services.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
@Tag(name = "Pets", description = "Gerenciamento de pets")  // Swagger Tag
public class PetController {

    private final PetService petService;

    @Operation(summary = "Criar um novo pet")
    @PostMapping
    public ResponseEntity<PetResponse> createPet(@RequestBody PetCreateRequest request) {
        return ResponseEntity.ok(petService.createPet(request));
    }

    @Operation(summary = "Atualizar um pet existente")
    @PutMapping("/{id}")
    public ResponseEntity<PetResponse> updatePet(@PathVariable UUID id, @RequestBody PetCreateRequest request) {
        return ResponseEntity.ok(petService.updatePet(id, request));
    }

    @Operation(summary = "Buscar um pet pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<PetResponse> getPetById(@PathVariable UUID id) {
        return ResponseEntity.ok(petService.getPetById(id));
    }

    @Operation(summary = "Listar todos os pets")
    @GetMapping
    public ResponseEntity<List<PetResponse>> getAllPets() {
        return ResponseEntity.ok(petService.getAllPets());
    }

    @Operation(summary = "Deletar um pet")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable UUID id) {
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }
}

