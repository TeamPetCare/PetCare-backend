package com.application.petcare.services;

import com.application.petcare.dto.pet.PetCreateRequest;
import com.application.petcare.dto.pet.PetPetsListResponse;
import com.application.petcare.dto.pet.PetPetsListUpdateRequest;
import com.application.petcare.dto.pet.PetResponse;
import com.application.petcare.entities.Pet;

import java.util.List;

public interface PetService {
    PetResponse createPet(PetCreateRequest request);

    PetResponse updatePet(Integer id, PetCreateRequest request);

    PetResponse updatePetPetsList(Integer id, PetPetsListUpdateRequest request);

    PetResponse getPetById(Integer id);

    List<PetResponse> getAllPets();

    List<Pet> getAllPetsByIds(List<Integer> ids);

    List<PetPetsListResponse> getAllPetsPetsList();

    void deletePet(Integer id);

    void deletePetPetsList(List<Integer> petIds);
}
