package com.application.petcare.services.impl;

import com.application.petcare.dto.pet.PetCreateRequest;
import com.application.petcare.dto.pet.PetResponse;
import com.application.petcare.entities.*;
import com.application.petcare.repository.*;
import com.application.petcare.services.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    private final UserRepository userRepository;
    private final EspecieRepository especieRepository;
    private final RacaRepository racaRepository;
    private final SizeRepository sizeRepository;

    @Override
    public PetResponse createPet(PetCreateRequest request) {
        // Substituindo Customer por User
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Especie especie = especieRepository.findById(request.getEspecieId())
                .orElseThrow(() -> new RuntimeException("Especie not found"));

        Raca raca = racaRepository.findById(request.getRacaId())
                .orElseThrow(() -> new RuntimeException("Raca not found"));

        Size size = sizeRepository.findById(request.getSizeId())
                .orElseThrow(() -> new RuntimeException("Size not found"));

        Pet pet = Pet.builder()
                .name(request.getName())
                .user(user) // Alterando o relacionamento para User
                .especie(especie)
                .raca(raca)
                .birthDate(request.getBirthDate())
                .sexo(request.getSexo())
                .color(request.getColor())
                .weight(request.getWeight())
                .size(size)
                .notes(request.getNotes())
                .build();

        Pet savedPet = petRepository.save(pet);
        return mapToResponse(savedPet);
    }

    @Override
    public PetResponse updatePet(Integer id, PetCreateRequest request) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pet not found"));

        // Atualizando as informações
        pet.setName(request.getName());
        pet.setBirthDate(request.getBirthDate());
        pet.setSexo(request.getSexo());
        pet.setColor(request.getColor());
        pet.setWeight(request.getWeight());
        pet.setNotes(request.getNotes());

        // Relacionamentos
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        pet.setUser(user); // Atualizando para User

        Especie especie = especieRepository.findById(request.getEspecieId())
                .orElseThrow(() -> new IllegalArgumentException("Especie not found"));
        pet.setEspecie(especie);

        Raca raca = racaRepository.findById(request.getRacaId())
                .orElseThrow(() -> new IllegalArgumentException("Raca not found"));
        pet.setRaca(raca);

        Size size = sizeRepository.findById(request.getSizeId())
                .orElseThrow(() -> new IllegalArgumentException("Size not found"));
        pet.setSize(size);

        Pet updatedPet = petRepository.save(pet);
        return mapToResponse(updatedPet);
    }

    @Override
    public PetResponse getPetById(Integer id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pet not found"));
        return mapToResponse(pet);
    }

    @Override
    public List<PetResponse> getAllPets() {
        return petRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePet(Integer id) {
        petRepository.deleteById(id);
    }

    private PetResponse mapToResponse(Pet pet) {
        return PetResponse.builder()
                .id(pet.getId())
                .name(pet.getName())
                .userId(pet.getUser().getId()) // Alterando para userId
                .especieId(pet.getEspecie().getId())
                .racaId(pet.getRaca().getId())
                .birthDate(pet.getBirthDate())
                .sexo(pet.getSexo())
                .color(pet.getColor())
                .weight(pet.getWeight())
                .sizeId(pet.getSize().getId())
                .notes(pet.getNotes())
                .build();
    }
}
