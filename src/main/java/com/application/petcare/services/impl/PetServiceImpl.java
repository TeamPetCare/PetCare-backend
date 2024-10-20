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
    private final SpecieRepository especieRepository;
    private final RaceRepository racaRepository;
    private final SizeRepository sizeRepository;

    @Override
    public PetResponse createPet(PetCreateRequest request) {
        log.info("Creating pet: {}", request);

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new BadRequestException("User not found"));

        Specie specie = especieRepository.findById(request.getEspecieId())
                .orElseThrow(() -> new BadRequestException("Especie not found"));

        Race race = racaRepository.findById(request.getRacaId())
                .orElseThrow(() -> new BadRequestException("Raca not found"));

        Size size = sizeRepository.findById(request.getSizeId())
                .orElseThrow(() -> new BadRequestException("Size not found"));

        Pet pet = Pet.builder()
                .name(request.getName())
                .user(user)
                .specie(specie)
                .race(race)
                .birthDate(request.getBirthDate())
                .sexo(request.getSexo())
                .color(request.getColor())
                .weight(request.getWeight())
                .size(size)
                .notes(request.getNotes())
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

        // Atualizando as informações
        pet.setName(request.getName());
        pet.setBirthDate(request.getBirthDate());
        pet.setSexo(request.getSexo());
        pet.setColor(request.getColor());
        pet.setWeight(request.getWeight());
        pet.setNotes(request.getNotes());

        // Relacionamentos
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new BadRequestException("User not found"));
        pet.setUser(user);

        Specie specie = especieRepository.findById(request.getEspecieId())
                .orElseThrow(() -> new BadRequestException("Especie not found"));
        pet.setSpecie(specie);

        Race race = racaRepository.findById(request.getRacaId())
                .orElseThrow(() -> new BadRequestException("Raca not found"));
        pet.setRace(race);

        Size size = sizeRepository.findById(request.getSizeId())
                .orElseThrow(() -> new BadRequestException("Size not found"));
        pet.setSize(size);

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
                .userId(pet.getUser().getId())
                .especieId(pet.getSpecie().getId())
                .racaId(pet.getRace().getId())
                .birthDate(pet.getBirthDate())
                .sexo(pet.getSexo())
                .color(pet.getColor())
                .weight(pet.getWeight())
                .sizeId(pet.getSize().getId())
                .notes(pet.getNotes())
                .build();
    }
}
