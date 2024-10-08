package com.application.petcare.services;

import com.application.petcare.dto.pet.PetCreateRequest;
import com.application.petcare.dto.pet.PetResponse;

import java.util.List;

public interface PetService {
    PetResponse createPet(PetCreateRequest request);

    PetResponse updatePet(Integer id, PetCreateRequest request);

    PetResponse getPetById(Integer id);

    List<PetResponse> getAllPets();

    void deletePet(Integer id);
}
