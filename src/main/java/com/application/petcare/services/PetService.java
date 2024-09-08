package com.application.petcare.services;

import com.application.petcare.dto.pet.PetCreateRequest;
import com.application.petcare.dto.pet.PetResponse;

import java.util.List;
import java.util.UUID;

public interface PetService {
    PetResponse createPet(PetCreateRequest request);

    PetResponse updatePet(UUID id, PetCreateRequest request);

    PetResponse getPetById(UUID id);

    List<PetResponse> getAllPets();

    void deletePet(UUID id);
}
