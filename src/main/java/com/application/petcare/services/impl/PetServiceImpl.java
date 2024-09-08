package com.application.petcare.services.impl;

import com.application.petcare.dto.pet.PetCreateRequest;
import com.application.petcare.dto.pet.PetResponse;
import com.application.petcare.entities.*;
import com.application.petcare.repository.*;
import com.application.petcare.services.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    private final CustomerRepository customerRepository;
    private final EspecieRepository especieRepository;
    private final RacaRepository racaRepository;
    private final SizeRepository sizeRepository;

    @Override
    public PetResponse createPet(PetCreateRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Especie especie = especieRepository.findById(request.getEspecieId())
                .orElseThrow(() -> new RuntimeException("Especie not found"));

        Raca raca = racaRepository.findById(request.getEspecieId())
                .orElseThrow(() -> new RuntimeException("Raca not found"));

        Size size = sizeRepository.findById(request.getEspecieId())
                .orElseThrow(() -> new RuntimeException("Size not found"));

        Pet pet = Pet.builder()
                .name(request.getName())
                .customer(customer)
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
    public PetResponse updatePet(UUID id, PetCreateRequest request) {
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
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        pet.setCustomer(customer);

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
    public PetResponse getPetById(UUID id) {
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
    public void deletePet(UUID id) {
        petRepository.deleteById(id);
    }

    private PetResponse mapToResponse(Pet pet){
        return PetResponse.builder()
                .id(pet.getId())
                .name(pet.getName())
                .customerId(pet.getCustomer().getId())
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
