package com.application.petcare.services;

import com.application.petcare.dto.pet.PetCreateRequest;
import com.application.petcare.dto.pet.PetPetsListResponse;
import com.application.petcare.dto.pet.PetResponse;
import com.application.petcare.entities.Pet;

import java.util.List;

public interface PetService {
    PetResponse createPet(PetCreateRequest request);

    PetResponse updatePet(Integer id, PetCreateRequest request);

    PetResponse getPetById(Integer id);

    List<PetResponse> getAllPets();

    List<PetPetsListResponse> getAllPetsPetsList();

    void deletePet(Integer id);
}
