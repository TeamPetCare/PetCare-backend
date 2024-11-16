package com.application.petcare.services.impl;

import com.application.petcare.dto.pet.PetCreateRequest;
import com.application.petcare.dto.pet.PetPetsListResponse;
import com.application.petcare.dto.pet.PetPetsListUpdateRequest;
import com.application.petcare.dto.pet.PetResponse;
import com.application.petcare.entities.*;
import com.application.petcare.enums.Role;
import com.application.petcare.exceptions.BadRoleException;
import com.application.petcare.exceptions.PetNotFoundException;
import com.application.petcare.exceptions.ResourceNotFoundException;
import com.application.petcare.repository.*;
import com.application.petcare.services.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.application.petcare.exceptions.BadRequestException;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final SpecieRepository specieRepository;
    private final RaceRepository raceRepository;
    private final SizeRepository sizeRepository;
    private final PlansRepository plansRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Override
    public PetResponse createPet(PetCreateRequest request) {
        log.info("Creating pet: {}", request);

        User possibleCustomer = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if(!possibleCustomer.getRole().equals(Role.ROLE_CUSTOMER)){
            throw new BadRoleException("User is not a customer");
        }

        Specie specie = specieRepository.findById(request.getSpecieId())
                .orElseThrow(() -> new BadRequestException("Especie not found"));

        Race race = raceRepository.findById(request.getRaceId())
                .orElseThrow(() -> new BadRequestException("Raca not found"));

        Size size = sizeRepository.findById(request.getSizeId())
                .orElseThrow(() -> new BadRequestException("Size not found"));

        Plans plans = plansRepository.findById(request.getPlanId())
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));

        Pet pet = Pet.builder()
                .name(request.getName())
                .specie(specie)
                .race(race)
                .petImg(request.getPetImg())
                .birthdate(request.getBirthdate())
                .gender(request.getGender())
                .color(request.getColor())
                .estimatedWeight(request.getEstimatedWeight())
                .size(size)
                .petObservations(request.getPetObservations())
                .plan(plansRepository.findById(request.getPlanId())
                        .orElseThrow(() -> new ResourceNotFoundException("Plan not found")))
                .user(possibleCustomer)
                .build();

        Pet savedPet = petRepository.save(pet);
        log.info("Pet created successfully: {}", savedPet);
        return mapToResponse(savedPet);
    }

    @Override
    public PetResponse updatePet(Integer id, PetCreateRequest request) {
        log.info("Updating pet with id: {}", id);

        User possibleCustomer = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if(!possibleCustomer.getRole().equals(Role.ROLE_CUSTOMER)){
            throw new BadRoleException("User is not a customer");
        }

        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException("Pet not found"));

        Size size = sizeRepository.findById(request.getSizeId())
                .orElseThrow(() -> new ResourceNotFoundException("Size not found"));

        Specie specie = specieRepository.findById(request.getSpecieId())
                .orElseThrow(() -> new ResourceNotFoundException("Specie not found"));

        Race race = raceRepository.findById(request.getRaceId())
                .orElseThrow(() -> new ResourceNotFoundException("Race not found"));

        Plans plans = plansRepository.findById(request.getPlanId())
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));

        // Atualizando as informações
        pet.setName(request.getName());

        pet.setPetImg(request.getPetImg());
        pet.setBirthdate(request.getBirthdate());
        pet.setGender(request.getGender());
        pet.setColor(request.getColor());
        pet.setEstimatedWeight(request.getEstimatedWeight());
        pet.setPetObservations(request.getPetObservations());
        pet.setSize(size);
        pet.setSpecie(specie);
        pet.setRace(race);
        pet.setPlan(plans);
        pet.setUser(possibleCustomer);

        Pet updatedPet = petRepository.save(pet);
        log.info("Pet updated successfully: {}", updatedPet);
        return mapToResponse(updatedPet);
    }

    @Override
    public PetResponse updatePetPetsList(Integer id, PetPetsListUpdateRequest request) {
        User possibleCustomer = userRepository.findById(request.getDono())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if(!possibleCustomer.getRole().equals(Role.ROLE_CUSTOMER)){
            throw new BadRoleException("User is not a customer");
        }

        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException("Pet not found"));

        Size size = sizeRepository.findById(request.getPorte())
                .orElseThrow(() -> new ResourceNotFoundException("Size not found"));

        Specie specie = specieRepository.findById(request.getEspecie())
                .orElseThrow(() -> new ResourceNotFoundException("Specie not found"));

        Race race = raceRepository.findById(request.getRaca())
                .orElseThrow(() -> new ResourceNotFoundException("Race not found"));

        Plans plans = plansRepository.findById(request.getPlano())
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));

        // Atualizando as informações
        pet.setName(request.getPet());

//        pet.setPetImg(request.());
        pet.setBirthdate(request.getDtNascimento());
        pet.setGender(request.getSexo());
        pet.setColor(request.getCor());
        pet.setEstimatedWeight(request.getPesoEstimado());
        pet.setPetObservations(request.getObservacoes());
        pet.setSize(size);
        pet.setSpecie(specie);
        pet.setRace(race);
        pet.setPlan(plans);
        pet.setUser(possibleCustomer);

        Pet updatedPet = petRepository.save(pet);
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
    public List<PetPetsListResponse> getAllPetsPetsList() {
        return maptoPetPetsListResponse(petRepository.findAll().stream().collect(Collectors.toList()));
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

    @Override
    public void deletePetPetsList(List<Integer> petIds) {
        List<Integer> notFoundPets = new ArrayList<>();
        for (int i = 0; i < petIds.size(); i++) {
            if (!petRepository.existsById(petIds.get(i))) {
                notFoundPets.add(petIds.get(i));
            } else {
                petRepository.deleteById(petIds.get(i));
            }
        }

        if (!notFoundPets.isEmpty()) {
            throw new PetNotFoundException("Pets not found: " + notFoundPets.toString());
        }
    }


    public List<PetPetsListResponse> maptoPetPetsListResponse(List<Pet> pets){
        List<PetPetsListResponse> petPetsListResponses = new ArrayList<>();
        for (int i = 0; i < pets.size(); i++) {
            petPetsListResponses.add(PetPetsListResponse.builder()
                    .id(pets.get(i).getId())
                    .name(pets.get(i).getName())
                    .gender(pets.get(i).getGender())
                    .color(pets.get(i).getColor())
                    .estimatedWeight(pets.get(i).getEstimatedWeight())
                    .birthdate(pets.get(i).getBirthdate())
                    .petObservations(pets.get(i).getPetObservations())
                    .petImg(pets.get(i).getPetImg())
                    .plan(pets.get(i).getPlan())
                    .specie(pets.get(i).getSpecie())
                    .race(pets.get(i).getRace())
                    .size(pets.get(i).getSize())
                            .userId(pets.get(i).getUser().getId())
                    .lastSchedule(scheduleRepository.findTopByPetIdOrderByScheduleDateDesc(pets.get(i).getId()).getScheduleDate())
                    .totalSchedules(scheduleRepository.countByPetId(pets.get(i).getId())).build()
            );
        }
        return petPetsListResponses;
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
                .specieId(pet.getSpecie().getId())
                .raceId(pet.getRace().getId())
                .sizeId(pet.getSize().getId())
                .planId(pet.getPlan().getId())
                .userId(pet.getUser().getId())
                .build();
    }
}
