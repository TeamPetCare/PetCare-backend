package com.application.petcare.services.impl;

import com.application.petcare.dto.pet.PetPetsListUpdateRequest;
import com.application.petcare.entities.Specie;
import com.application.petcare.entities.Race;
import com.application.petcare.entities.Size;
import com.application.petcare.entities.Plans;

import java.math.BigDecimal;

import com.application.petcare.dto.pet.PetCreateRequest;
import com.application.petcare.dto.pet.PetResponse;
import com.application.petcare.entities.Pet;
import com.application.petcare.entities.User;
import com.application.petcare.enums.Role;
import com.application.petcare.exceptions.BadRoleException;
import com.application.petcare.exceptions.PetNotFoundException;
import com.application.petcare.exceptions.ResourceNotFoundException;
import com.application.petcare.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PetServiceImplTest {

    @Mock
    private PetRepository petRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SpecieRepository specieRepository;

    @Mock
    private RaceRepository raceRepository;

    @Mock
    private SizeRepository sizeRepository;

    @Mock
    private PlansRepository plansRepository;

    @InjectMocks
    private PetServiceImpl petService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve criar um pet com sucesso")
    void createPet_shouldCreateAndReturnPetResponse() {
        // Arrange
        PetCreateRequest request = PetCreateRequest.builder()
                .name("Buddy")
                .userId(1)
                .specieId(2)
                .raceId(3)
                .sizeId(4)
                .planId(5)
                .build();

        User user = User.builder().id(1).role(Role.ROLE_CUSTOMER).build();
        Specie specie = Specie.builder().id(2).build();
        Race race = Race.builder().id(3).raceType("Labrador").price(100.0).build();
        Size size = Size.builder().id(4).sizeType("Large").price(new BigDecimal("50.0")).build();
        Plans plan = Plans.builder().id(5).build();

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(specieRepository.findById(2)).thenReturn(Optional.of(specie));
        when(raceRepository.findById(3)).thenReturn(Optional.of(race));
        when(sizeRepository.findById(4)).thenReturn(Optional.of(size));
        when(plansRepository.findById(5)).thenReturn(Optional.of(plan));

        Pet savedPet = Pet.builder()
                .id(10)
                .name("Buddy")
                .specie(specie)
                .race(race)
                .size(size)
                .plan(plan)
                .user(user) // Associando o usuário ao Pet
                .build();

        when(petRepository.save(any(Pet.class))).thenReturn(savedPet);

        // Act
        PetResponse response = petService.createPet(request);

        // Assert
        assertNotNull(response);
        assertEquals("Buddy", response.getName());
        assertEquals(10, response.getId());
        assertEquals(2, response.getSpecieId());
        assertEquals(3, response.getRaceId());
        assertEquals(4, response.getSizeId());
        assertEquals(1, response.getUserId()); // Validando o ID do usuário associado
        verify(petRepository, times(1)).save(any(Pet.class));
    }



    @Test
    @DisplayName("Deve lançar exceção se o usuário não for encontrado ao criar pet")
    void createPet_shouldThrowExceptionWhenUserNotFound() {
        // Arrange
        PetCreateRequest request = PetCreateRequest.builder().userId(1).build();
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> petService.createPet(request));
        verify(petRepository, never()).save(any(Pet.class));
    }

    @Test
    @DisplayName("Deve lançar exceção se o usuário não for um cliente ao criar pet")
    void createPet_shouldThrowExceptionWhenUserNotCustomer() {
        // Arrange
        PetCreateRequest request = PetCreateRequest.builder().userId(1).build();
        User user = User.builder().id(1).role(Role.ROLE_EMPLOYEE).build();
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        // Act & Assert
        assertThrows(BadRoleException.class, () -> petService.createPet(request));
        verify(petRepository, never()).save(any(Pet.class));
    }

    @Test
    @DisplayName("Deve retornar um pet pelo ID")
    void getPetById_shouldReturnPetResponse() {
        // Arrange
        Specie specie = Specie.builder().id(2).build();
        Race race = Race.builder().id(3).raceType("Labrador").build();
        Size size = Size.builder().id(4).sizeType("Large").build();
        Plans plan = Plans.builder().id(5).build();
        User user = User.builder().id(1).build();

        Pet pet = Pet.builder()
                .id(10)
                .name("Buddy")
                .specie(specie) // Configurando o Specie
                .race(race)
                .size(size)
                .plan(plan)
                .user(user)
                .build();

        when(petRepository.findById(10)).thenReturn(Optional.of(pet));

        // Act
        PetResponse response = petService.getPetById(10);

        // Assert
        assertNotNull(response);
        assertEquals("Buddy", response.getName());
        assertEquals(2, response.getSpecieId());
        assertEquals(3, response.getRaceId());
        assertEquals(4, response.getSizeId());
        assertEquals(1, response.getUserId());
        verify(petRepository, times(1)).findById(10);
    }


    @Test
    @DisplayName("Deve lançar exceção se o pet não for encontrado pelo ID")
    void getPetById_shouldThrowExceptionWhenNotFound() {
        // Arrange
        when(petRepository.findById(10)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PetNotFoundException.class, () -> petService.getPetById(10));
        verify(petRepository, times(1)).findById(10);
    }

    @Test
    @DisplayName("Deve deletar um pet com sucesso")
    void deletePet_shouldDeleteSuccessfully() {
        // Arrange
        when(petRepository.existsById(10)).thenReturn(true);

        // Act
        petService.deletePet(10);

        // Assert
        verify(petRepository, times(1)).deleteById(10);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar deletar um pet inexistente")
    void deletePet_shouldThrowExceptionWhenNotFound() {
        // Arrange
        when(petRepository.existsById(10)).thenReturn(false);

        // Act & Assert
        assertThrows(PetNotFoundException.class, () -> petService.deletePet(10));
        verify(petRepository, never()).deleteById(10);
    }

    @Test
    @DisplayName("Deve atualizar as informações de um pet com sucesso")
    void updatePetPetsList_shouldUpdateAndReturnPetResponse() {
        // Arrange
        Integer petId = 10;

        PetPetsListUpdateRequest request = PetPetsListUpdateRequest.builder()
                .id(petId)
                .pet("Updated Buddy")
                .especie(2)
                .sexo("Male")
                .raca(3)
                .dtNascimento(LocalDate.of(2020, 1, 1))
                .porte(4)
                .pesoEstimado(25.0)
                .cor("Brown")
                .dono(1)
                .observacoes("Healthy")
                .plano(5)
                .build();

        User user = User.builder().id(1).role(Role.ROLE_CUSTOMER).build();
        Specie specie = Specie.builder().id(2).build();
        Race race = Race.builder().id(3).raceType("Labrador").build();
        Size size = Size.builder().id(4).sizeType("Large").build();
        Plans plan = Plans.builder().id(5).build();

        Pet existingPet = Pet.builder()
                .id(petId)
                .name("Buddy")
                .specie(specie)
                .race(race)
                .size(size)
                .plan(plan)
                .user(user)
                .build();

        Pet updatedPet = Pet.builder()
                .id(petId)
                .name("Updated Buddy")
                .specie(specie)
                .race(race)
                .size(size)
                .plan(plan)
                .user(user)
                .birthdate(LocalDate.of(2020, 1, 1))
                .gender("Male")
                .color("Brown")
                .estimatedWeight(25.0)
                .petObservations("Healthy")
                .build();

        when(petRepository.findById(petId)).thenReturn(Optional.of(existingPet));
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(specieRepository.findById(2)).thenReturn(Optional.of(specie));
        when(raceRepository.findById(3)).thenReturn(Optional.of(race));
        when(sizeRepository.findById(4)).thenReturn(Optional.of(size));
        when(plansRepository.findById(5)).thenReturn(Optional.of(plan));
        when(petRepository.save(any(Pet.class))).thenReturn(updatedPet);

        // Act
        PetResponse response = petService.updatePetPetsList(petId, request);

        // Assert
        assertNotNull(response);
        assertEquals("Updated Buddy", response.getName());
        assertEquals(2, response.getSpecieId());
        assertEquals(3, response.getRaceId());
        assertEquals(4, response.getSizeId());
        assertEquals(1, response.getUserId());
        assertEquals(LocalDate.of(2020, 1, 1), response.getBirthdate());
        assertEquals("Male", response.getGender());
        assertEquals("Brown", response.getColor());
        assertEquals(25.0, response.getEstimatedWeight());
        assertEquals("Healthy", response.getPetObservations());
        verify(petRepository, times(1)).save(any(Pet.class));
    }

}
