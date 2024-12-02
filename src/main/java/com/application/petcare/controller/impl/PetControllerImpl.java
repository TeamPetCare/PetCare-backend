package com.application.petcare.controller.impl;

import com.application.petcare.controller.PetController;
import com.application.petcare.dto.pet.PetCreateRequest;
import com.application.petcare.dto.pet.PetPetsListResponse;
import com.application.petcare.dto.pet.PetPetsListUpdateRequest;
import com.application.petcare.dto.pet.PetResponse;
import com.application.petcare.entities.Pet;
import com.application.petcare.services.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PetControllerImpl implements PetController {

    private final PetService petService;

    @Override
    public ResponseEntity<PetResponse> createPet(PetCreateRequest request) {
        return ResponseEntity.ok(petService.createPet(request));
    }

    @Override
    public ResponseEntity<PetResponse> updatePet(Integer id, PetCreateRequest request) {
        return ResponseEntity.ok(petService.updatePet(id, request));
    }

    @Override
    public ResponseEntity<PetResponse> updatePetPetsList(Integer id, PetPetsListUpdateRequest request) {
        return ResponseEntity.ok().body(petService.updatePetPetsList(id, request));
    }

    @Override
    public ResponseEntity<PetResponse> getPetById(Integer id) {
        return ResponseEntity.ok(petService.getPetById(id));
    }

    @Override
    public ResponseEntity<List<PetResponse>> getAllPets() {
        return ResponseEntity.ok(petService.getAllPets());
    }

    @Override
    public ResponseEntity<List<PetPetsListResponse>> getAllPetsPetsList() {
        return ResponseEntity.ok().body(petService.getAllPetsPetsList());
    }

    @Override
    public ResponseEntity<List<Pet>> getAllPetsByIds(List<Integer> ids) {
        return ResponseEntity.ok().body(petService.getAllPetsByIds(ids));
    }

    @Override
    public ResponseEntity<Void> deletePet(Integer id) {
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deletePetPetsList(List<Integer> ids) {
        petService.deletePetPetsList(ids);
        return ResponseEntity.noContent().build();
    }
}
