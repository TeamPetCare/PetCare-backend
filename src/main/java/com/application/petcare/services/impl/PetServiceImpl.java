package com.application.petcare.services.impl;

import com.application.petcare.dto.pet.PetCreateRequest;
import com.application.petcare.dto.pet.PetResponse;
import com.application.petcare.entities.*;
import com.application.petcare.exceptions.PetNotFoundException;
import com.application.petcare.repository.*;
import com.application.petcare.services.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.application.petcare.exceptions.BadRequestException;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final UserRepository userRepository;
    private final SpecieRepository specieRepository;
    private final RaceRepository racaRepository;
    private final SizeRepository sizeRepository;

    @Override
    public PetResponse createPet(PetCreateRequest request) {
        log.info("Creating pet: {}", request);

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new BadRequestException("User not found"));

        Specie specie = specieRepository.findById(request.getSpecieId())
                .orElseThrow(() -> new BadRequestException("Especie not found"));

        Race race = racaRepository.findById(request.getRaceId())
                .orElseThrow(() -> new BadRequestException("Raca not found"));

        Size size = sizeRepository.findById(request.getSizeId())
                .orElseThrow(() -> new BadRequestException("Size not found"));

        Pet pet = Pet.builder()
                .name(request.getName())
                .user(user)
                .specie(specie)
                .race(race)
                .petImg(request.getPetImg())
                .birthdate(request.getBirthdate())
                .gender(request.getGender())
                .color(request.getColor())
                .estimatedWeight(request.getEstimatedWeight())
                .size(size)
                .petObservations(request.getPetObservations())
                .build();

        Pet savedPet = petRepository.save(pet);
        log.info("Pet created successfully: {}", savedPet);
        return mapToResponse(savedPet);
    }

    @Override
    public PetResponse updatePet(Integer id, PetCreateRequest request) {
        log.info("Updating pet with id: {}", id);

        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException("Pet not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new BadRequestException("User not found"));

        Size size = sizeRepository.findById(request.getSizeId())
                .orElseThrow(() -> new BadRequestException("Size not found"));

        Specie specie = specieRepository.findById(request.getSpecieId())
                .orElseThrow(() -> new BadRequestException("Specie not found"));

        Race race = racaRepository.findById(request.getRaceId())
                .orElseThrow(() -> new BadRequestException("Race not found"));

        // Atualizando as informações
        pet.setName(request.getName());

        pet.setPetImg(request.getPetImg());
        pet.setBirthdate(request.getBirthdate());
        pet.setGender(request.getGender());
        pet.setColor(request.getColor());
        pet.setEstimatedWeight(request.getEstimatedWeight());
        pet.setPetObservations(request.getPetObservations());
        pet.setUser(user);
        pet.setSize(size);
        pet.setSpecie(specie);
        pet.setRace(race);

        Pet updatedPet = petRepository.save(pet);
        log.info("Pet updated successfully: {}", updatedPet);
        return mapToResponse(updatedPet);
    }

    @Override
    public PetResponse getPetById(Integer id) {
        log.info("Fetching pet by id: {}", id);
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException("Pet not found"));
        return mapToResponse(pet);
    }

    @Override
    public List<PetResponse> getAllPets() {
        log.info("Fetching all pets");
        return petRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePet(Integer id) {
        log.info("Deleting pet with id: {}", id);
        if (!petRepository.existsById(id)) {
            throw new PetNotFoundException("Pet not found");
        }
        petRepository.deleteById(id);
        log.info("Pet deleted successfully with id: {}", id);
    }

    private PetResponse mapToResponse(Pet pet) {
        return PetResponse.builder()
                .id(pet.getId())
                .name(pet.getName())
                .gender(pet.getGender())
                .color(pet.getColor())
                .estimatedWeight(pet.getEstimatedWeight())
                .birthdate(pet.getBirthdate())
                .petObservations(pet.getPetObservations())
                .petImg(pet.getPetImg())
                .userId(pet.getUser().getId())
                .specieId(pet.getSpecie().getId())
                .raceId(pet.getRace().getId())
                .sizeId(pet.getSize().getId())
                .build();
    }
}
