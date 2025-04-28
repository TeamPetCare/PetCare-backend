package com.application.petcare.controller;

import com.application.petcare.dto.pet.PetCreateRequest;
import com.application.petcare.dto.pet.PetPetsListResponse;
import com.application.petcare.dto.pet.PetPetsListUpdateRequest;
import com.application.petcare.dto.pet.PetResponse;
import com.application.petcare.entities.Pet;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pets", description = "Gerenciamento de pets")
@RequestMapping("/api/pets")
public interface PetController {

    @Operation(summary = "Criar um novo pet")
    @PostMapping
    ResponseEntity<PetResponse> createPet(@RequestBody PetCreateRequest request);

    @Operation(summary = "Criar uma lista de Pets")
    @PostMapping("/list")
    ResponseEntity<List<Integer>> createListOfPet(@RequestBody List<PetCreateRequest> request);

    @Operation(summary = "Atualizar um pet existente")
    @PutMapping("/{id}")
    ResponseEntity<PetResponse> updatePet(@PathVariable Integer id, @RequestBody PetCreateRequest request);

    @Operation(summary = "Atualizar um pet na tela Clientes e Pets existente")
    @PutMapping("pets-list/{id}")
    ResponseEntity<PetResponse> updatePetPetsList(@PathVariable Integer id,@RequestBody PetPetsListUpdateRequest request);

        @Operation(summary = "Buscar um pet pelo ID")
    @GetMapping("/{id}")
    ResponseEntity<PetResponse> getPetById(@PathVariable Integer id);

    @Operation(summary = "Listar todos os pets")
    @GetMapping
    ResponseEntity<List<PetResponse>> getAllPets();

    @Operation(summary = "Lista todos os pets de um usuário")
    @GetMapping
    ResponseEntity<List<Pet>> getAllPetsByUserId(Integer userId);

    @Operation(summary = "Listar todos os pets na tela Clientes e Pets")
    @GetMapping("/pets-list")
    ResponseEntity<List<PetPetsListResponse>> getAllPetsPetsList();

    @Operation(summary = "Listar pets com uma lista de ids")
    @GetMapping("/list-by-ids")
    ResponseEntity<List<Pet>> getAllPetsByIds(@RequestParam List<Integer> ids);

    @Operation(summary = "Deletar um pet")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePet(@PathVariable Integer id);

    @Operation(summary = "Deletar uma lista de pets")
    @DeleteMapping("/pets-list")
    ResponseEntity<Void> deletePetPetsList(@RequestBody List<Integer> ids);

    @Operation(summary = "Pegar todas Especies, Tamanhos e Raças")
    @DeleteMapping("/data")
    ResponseEntity<List> getSpeciesSizesAndRaces();

}
