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
import com.application.petcare.utils.ImageDatabase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    private final ImageDatabase imageDatabase;

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


        Plans plans;
        if(request.getPlanId() == null){
            plans = null;
        }else{
            plans = plansRepository.findById(request.getPlanId()).get();
        }

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
                .deletedAt(null)
                .petObservations(request.getPetObservations())
                .plan(plans)
                .user(possibleCustomer)
                .build();

        Pet savedPet = petRepository.save(pet);
        savedPet.setPetImg(imageDatabase.downloadImagem(savedPet.getId(), false));
        petRepository.save(savedPet);
        log.info("Pet created successfully: {}", savedPet);
        return mapToResponse(savedPet);
    }

    public List<Integer> createListOfPet(List<PetCreateRequest> request){
        List<Integer> petIds = new ArrayList<>();
        for (int i = 0; i < request.size(); i++) {
            log.info("Creating pet: {}", request);

            User possibleCustomer = userRepository.findById(request.get(i).getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            if(!possibleCustomer.getRole().equals(Role.ROLE_CUSTOMER)){
                throw new BadRoleException("User is not a customer");
            }

            Specie specie = specieRepository.findById(request.get(i).getSpecieId())
                    .orElseThrow(() -> new BadRequestException("Especie not found"));

            Race race = raceRepository.findById(request.get(i).getRaceId())
                    .orElseThrow(() -> new BadRequestException("Raca not found"));

            Size size = sizeRepository.findById(request.get(i).getSizeId())
                    .orElseThrow(() -> new BadRequestException("Size not found"));


            Plans plans;
            if(request.get(i).getPlanId() == null){
                plans = null;
            }else{
                plans = plansRepository.findById(request.get(i).getPlanId()).get();
            }

            Pet pet = Pet.builder()
                    .name(request.get(i).getName())
                    .specie(specie)
                    .race(race)
                    .petImg(request.get(i).getPetImg())
                    .birthdate(request.get(i).getBirthdate())
                    .gender(request.get(i).getGender())
                    .color(request.get(i).getColor())
                    .estimatedWeight(request.get(i).getEstimatedWeight())
                    .size(size)
                    .deletedAt(null)
                    .petObservations(request.get(i).getPetObservations())
                    .plan(plans)
                    .user(possibleCustomer)
                    .build();

            Pet savedPet = petRepository.save(pet);
            savedPet.setPetImg(imageDatabase.downloadImagem(savedPet.getId(), false));
            petRepository.save(savedPet);
            log.info("Pet created successfully: {}", savedPet);
            petIds.add(savedPet.getId());
        }
        return(petIds);
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

        Plans plans;
        if(request.getPlanId() == null){
            plans = null;
        }else{
            plans = plansRepository.findById(request.getPlanId()).get();
        }

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

        Plans plans;
        if(request.getPlano() == null){
            plans = null;
        }else{
            plans = plansRepository.findById(request.getPlano()).get();
        }

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
        return petRepository.findAllByDeletedAtIsNull().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<Pet> getAllPetsByIds(List<Integer> ids) {
        return petRepository.findAllByIdInAndDeletedAtIsNull(ids);
    }

    @Override
    public List<Pet> getAllPetsByUserId(Integer userId) {
        return petRepository.findAllByUserIdInAndDeletedAtIsNull(userId);
    }

    @Override
    public List<PetPetsListResponse> getAllPetsPetsList() {
        return maptoPetPetsListResponse(petRepository.findAllByDeletedAtIsNull().stream().collect(Collectors.toList()));
    }

    @Override
    public void deletePet(Integer id) {
        log.info("Deleting pet with id: {}", id);
        Pet pet = petRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Pet not found"));
        pet.setDeletedAt(LocalDateTime.now());
        petRepository.save(pet);
        log.info("Pet deleted successfully with id: {}", id);
    }

    @Override
    public void deletePetPetsList(List<Integer> petIds) {
        List<Integer> notFoundPets = new ArrayList<>();
        for (int i = 0; i < petIds.size(); i++) {
            if (!petRepository.existsById(petIds.get(i))) {
                notFoundPets.add(petIds.get(i));
            } else {
                Pet pet = petRepository.findById(petIds.get(i))
                                .orElseThrow(() -> new ResourceNotFoundException("Pet not found"));
                pet.setDeletedAt(LocalDateTime.now());
                petRepository.save(pet);
            }
        }

        if (!notFoundPets.isEmpty()) {
            throw new PetNotFoundException("Pets not found: " + notFoundPets.toString());
        }
    }

    @Override
    public List getSpeciesSizesAndRaces() {
        List listaDados = List.of(
                specieRepository.findAllByDeletedAtIsNull(),
                sizeRepository.findAllByDeletedAtIsNull(),
                raceRepository.findAllByDeletedAtIsNull()
        );
        return listaDados;
    }


    public List<PetPetsListResponse> maptoPetPetsListResponse(List<Pet> pets){
        List<PetPetsListResponse> petPetsListResponses = new ArrayList<>();
        for (int i = 0; i < pets.size(); i++) {

            Schedule lastSchedule = scheduleRepository.findTopByPetIdOrderByScheduleDateDesc(pets.get(i).getId());
            LocalDateTime lastScheduleTime;
            if(lastSchedule != null){
                lastScheduleTime = lastSchedule.getScheduleDate();
            }else{
                lastScheduleTime = LocalDateTime.of(0,1,1,0,0,0);
            }

            Plans plans;
            if(pets.get(i).getPlan() == null){
                plans = null;
            }else {
                plans = pets.get(i).getPlan();
            }


            petPetsListResponses.add(PetPetsListResponse.builder()
                    .id(pets.get(i).getId())
                    .name(pets.get(i).getName())
                    .gender(pets.get(i).getGender())
                    .color(pets.get(i).getColor())
                    .estimatedWeight(pets.get(i).getEstimatedWeight())
                    .birthdate(pets.get(i).getBirthdate())
                    .petObservations(pets.get(i).getPetObservations())
                    .petImg(pets.get(i).getPetImg())
                    .plan(plans)
                    .specie(pets.get(i).getSpecie())
                    .race(pets.get(i).getRace())
                            .deletedAt(pets.get(i).getDeletedAt())
                    .size(pets.get(i).getSize())
                            .userId(pets.get(i).getUser().getId())
                    .lastSchedule(lastScheduleTime)
                    .totalSchedules(scheduleRepository.countByPetId(pets.get(i).getId())).build()
            );
        }
        return petPetsListResponses;
    }

    private PetResponse mapToResponse(Pet pet) {

        Integer planId;
        if(pet.getPlan() == null){
            planId = null;
        }else{
            planId = pet.getPlan().getId();
        }

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
                .planId(planId)
                .userId(pet.getUser().getId())
                .build();
    }
}
