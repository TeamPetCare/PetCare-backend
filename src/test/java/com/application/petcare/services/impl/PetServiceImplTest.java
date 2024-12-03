package com.application.petcare.services.impl;

import com.application.petcare.dto.pet.*;
import com.application.petcare.entities.*;
import com.application.petcare.enums.Role;
import com.application.petcare.exceptions.*;
import com.application.petcare.repository.*;
import com.application.petcare.utils.ImageDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PetServiceImplTest {

    @Mock
    private PetRepository petRepository;

    @Mock
    private SpecieRepository specieRepository;

    @Mock
    private RaceRepository raceRepository;

    @Mock
    private SizeRepository sizeRepository;

    @Mock
    private PlansRepository plansRepository;

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ImageDatabase imageDatabase;

    @InjectMocks
    private PetServiceImpl petService;

    private PetCreateRequest petCreateRequest;
    private Pet pet;
    private User user;
    private Specie specie;
    private Race race;
    private Size size;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1);
        user.setRole(Role.ROLE_CUSTOMER);

        specie = new Specie();
        specie.setId(1);

        race = new Race();
        race.setId(1);

        size = new Size();
        size.setId(1);

        petCreateRequest = PetCreateRequest.builder()
                .userId(1)
                .name("Buddy")
                .specieId(1)
                .raceId(1)
                .sizeId(1)
                .birthdate(LocalDate.of(2020, 1, 1))
                .gender("Male")
                .color("Brown")
                .estimatedWeight(10.5)
                .build();

        pet = Pet.builder()
                .id(1)
                .name("Buddy")
                .specie(specie)
                .race(race)
                .size(size)
                .user(user)
                .birthdate(LocalDate.of(2020, 1, 1))
                .gender("Male")
                .color("Brown")
                .estimatedWeight(10.5)
                .build();
    }

    @Test
    void createPet_success() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(specieRepository.findById(1)).thenReturn(Optional.of(specie));
        when(raceRepository.findById(1)).thenReturn(Optional.of(race));
        when(sizeRepository.findById(1)).thenReturn(Optional.of(size));
        when(petRepository.save(any(Pet.class))).thenReturn(pet);

        PetResponse response = petService.createPet(petCreateRequest);

        assertNotNull(response);
        assertEquals("Buddy", response.getName());
        verify(petRepository, times(2)).save(any(Pet.class));
    }

    @Test
    void createPet_userNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> petService.createPet(petCreateRequest));
    }

    @Test
    void getPetById_success() {
        when(petRepository.findById(1)).thenReturn(Optional.of(pet));

        PetResponse response = petService.getPetById(1);

        assertNotNull(response);
        assertEquals(1, response.getId());
        assertEquals("Buddy", response.getName());
    }

    @Test
    void getPetById_notFound() {
        when(petRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(PetNotFoundException.class, () -> petService.getPetById(1));
    }

    @Test
    void getAllPets_success() {
        when(petRepository.findAllByDeletedAtIsNull()).thenReturn(Collections.singletonList(pet));

        List<PetResponse> responses = petService.getAllPets();

        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals("Buddy", responses.get(0).getName());
    }

    @Test
    void updatePet_success() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(petRepository.findById(1)).thenReturn(Optional.of(pet));
        when(specieRepository.findById(1)).thenReturn(Optional.of(specie));
        when(raceRepository.findById(1)).thenReturn(Optional.of(race));
        when(sizeRepository.findById(1)).thenReturn(Optional.of(size));
        when(petRepository.save(any(Pet.class))).thenReturn(pet);

        PetResponse response = petService.updatePet(1, petCreateRequest);

        assertNotNull(response);
        assertEquals("Buddy", response.getName());
        verify(petRepository, times(1)).save(any(Pet.class));
    }

    @Test
    void deletePet_success() {
        when(petRepository.findById(1)).thenReturn(Optional.of(pet));

        petService.deletePet(1);

        verify(petRepository, times(1)).save(any(Pet.class));
        assertNotNull(pet.getDeletedAt());
    }

    @Test
    void deletePet_notFound() {
        when(petRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> petService.deletePet(1));
    }

    @Test
    void deletePetPetsList_success() {
        when(petRepository.existsById(1)).thenReturn(true);
        when(petRepository.findById(1)).thenReturn(Optional.of(pet));

        petService.deletePetPetsList(Collections.singletonList(1));

        verify(petRepository, times(1)).save(any(Pet.class));
    }

    @Test
    void deletePetPetsList_petNotFound() {
        when(petRepository.existsById(1)).thenReturn(false);

        assertThrows(PetNotFoundException.class, () -> petService.deletePetPetsList(Collections.singletonList(1)));
    }
}
