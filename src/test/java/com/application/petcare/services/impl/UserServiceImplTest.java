package com.application.petcare.services.impl;

import com.application.petcare.dto.pet.PetCreateRequest;
import com.application.petcare.dto.pet.PetPetsListResponse;
import com.application.petcare.dto.user.*;
import com.application.petcare.entities.*;
import com.application.petcare.enums.Role;
import com.application.petcare.exceptions.DuplicateEntryFoundException;
import com.application.petcare.exceptions.ResourceNotFoundException;
import com.application.petcare.repository.PetRepository;
import com.application.petcare.repository.UserRepository;
import com.application.petcare.services.PetService;
import com.application.petcare.utils.ImageDatabase;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private PetServiceImpl petServiceImpl;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ImageDatabase imageDatabase;

    @InjectMocks
    private UserServiceImpl userService;

    private UserCreateRequest userCreateRequest;
    private UserUpdateRequest userUpdateRequest;
    private UserResponse userResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userCreateRequest = UserCreateRequest.builder()
                .name("John Doe")
                .userImg("image.jpg")
                .email("john.doe@example.com")
                .password("password123")
                .cellphone("123456789")
                .role(Role.ROLE_CUSTOMER)
                .street("Main St")
                .number(123)
                .complement("Apt 101")
                .cep("12345678")
                .district("Downtown")
                .city("Metropolis")
                .cnpjOwner(null)
                .roleEmployee(null)
                .disponibilityStatus(true)
                .cpfClient(null)
                .petIds(List.of(1, 2, 3))
                .build();

        userUpdateRequest = UserUpdateRequest.builder()
                .name("Jane Doe")
                .userImg("profile.jpg")
                .email("jane.doe@example.com")
                .password("newpassword456")
                .cellphone("987654321")
                .role(Role.ROLE_ADMIN) // Certifique-se de que Role seja um enum válido
                .street("Elm St")
                .number(456)
                .complement("Suite 202")
                .cep("87654321")
                .district("Uptown")
                .city("Gotham")
                .cnpjOwner(null)
                .roleEmployee("Manager")
                .disponibilityStatus(false)
                .cpfClient("12345678901")
                .petIds(List.of(4, 5, 6))
                .build();

        UserResponse.builder()
                .id(1)
                .name("John Doe")
                .userImg("profile.jpg")
                .email("john.doe@example.com")
                .password("password123")
                .cellphone("123456789")
                .role(Role.ROLE_ADMIN) // A Role precisa ser um valor válido do Enum Role
                .street("Main St")
                .number(123)
                .complement("Apt 4B")
                .cep("12345678")
                .district("Downtown")
                .city("Metropolis")
                .cnpjOwner(null)
                .roleEmployee("Employee")
                .disponibilityStatus(true)
                .cpfClient("12345678901")
                .petIds(List.of(2, 3)) // Supondo que existam Pets com IDs 2 e 3
                .build();
    }

    @Test
    void testCreateUser() {
        User user = User.builder()
                .id(1)
                .name("John Doe")
                .userImg("userImg.jpg")
                .email("john.doe@example.com")
                .password("encodedPassword")
                .cellphone("123456789")
                .role(Role.ROLE_CUSTOMER)
                .street("Street")
                .number(123)
                .complement("Apt 101")
                .cep("12345-678")
                .district("District")
                .city("City")
                .cnpjOwner(null)
                .roleEmployee(null)
                .disponibilityStatusEmployee(null)
                .cpfClient(null)
                .pets(new ArrayList<>())
                .build();

        when(userRepository.findByEmailAndDeletedAtIsNull(userCreateRequest.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(imageDatabase.downloadImagem(anyInt(), anyBoolean())).thenReturn("userImg.jpg");

        when(petRepository.findAllByIdInAndDeletedAtIsNull(anyList())).thenReturn(new ArrayList<>());  // Mock pets as empty

        UserResponse createdUser = userService.createUser(userCreateRequest);

        assertNotNull(createdUser);
        assertEquals("John Doe", createdUser.getName());
        verify(userRepository, times(2)).save(any(User.class));
    }

    @Test
    void testCreateUserWithDuplicateEmail() {
        when(userRepository.findByEmailAndDeletedAtIsNull(userCreateRequest.getEmail()))
                .thenReturn(Optional.of(new User()));

        assertThrows(DuplicateEntryFoundException.class, () -> userService.createUser(userCreateRequest));
    }

    @Test
    void testUpdateUser() {
        User existingUser = User.builder()
                .id(2)
                .name("John Doe Updated")
                .userImg("userImg.jpg")
                .email("john.doe@example.com")
                .password("encodedPassword")
                .cellphone("123456789")
                .role(Role.ROLE_CUSTOMER)
                .street("Street")
                .number(123)
                .complement("Apt 101")
                .cep("12345-678")
                .district("District")
                .city("City")
                .cnpjOwner(null)
                .roleEmployee(null)
                .disponibilityStatusEmployee(null)
                .pets(new ArrayList<>())
                .build();

        Specie specie = new Specie();
        specie.setId(1);

        Race race = new Race();
        race.setId(1);

        Size size = new Size();
        size.setId(1);

        Pet pet = Pet.builder()
                .id(1)
                .name("Buddy")
                .specie(specie)
                .race(race)
                .size(size)
                .user(existingUser)
                .birthdate(LocalDate.of(2020, 1, 1))
                .gender("Male")
                .color("Brown")
                .estimatedWeight(10.5)
                .build();

        existingUser.setPets(List.of(pet));

        UserUpdateRequest userUpdateRequest = UserUpdateRequest.builder()
                .name("John Doe Updated")
                .userImg("userImg.jpg")
                .email("john.doe@example.com")
                .password("encodedPassword")
                .cellphone("123456789")
                .role(Role.ROLE_CUSTOMER)
                .street("Street")
                .number(123)
                .complement("Apt 101")
                .cep("12345-678")
                .district("District")
                .city("City")
                .cnpjOwner(null)
                .roleEmployee(null)
                .disponibilityStatus(true)
                .cpfClient(null)
                .petIds(List.of(1))
                .build();

        when(userRepository.findById(2)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(existingUser);
        when(petRepository.findAllByIdInAndDeletedAtIsNull(userUpdateRequest.getPetIds()))
                .thenReturn(List.of(pet));
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        UserResponse updatedUser = userService.updateUser(2, userUpdateRequest);

        assertNotNull(updatedUser);
        assertEquals("John Doe Updated", updatedUser.getName());
        assertEquals("userImg.jpg", updatedUser.getUserImg());
        assertEquals("john.doe@example.com", updatedUser.getEmail());
        assertEquals("encodedPassword", updatedUser.getPassword());
        verify(userRepository, times(1)).save(any(User.class));
    }


    @Test
    void testUpdateUserNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(1, userUpdateRequest));
    }

    @Test
    void testDeleteUser() {
        User existingUser = User.builder()
                .id(1)
                .name("John Doe")
                .userImg("userImg.jpg")
                .email("john.doe@example.com")
                .password("encodedPassword")
                .cellphone("123456789")
                .role(Role.ROLE_CUSTOMER)
                .street("Street")
                .number(123)
                .complement("Apt 101")
                .cep("12345-678")
                .district("District")
                .city("City")
                .cnpjOwner(null)
                .roleEmployee(null)
                .disponibilityStatusEmployee(null)
                .pets(new ArrayList<>())
                .build();

        when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));

        userService.deleteUser(1);

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testDeleteUserNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(1));
    }

    @Test
    void testFindUserById() {
        User existingUser = User.builder()
                .id(1)
                .name("John Doe")
                .userImg("userImg.jpg")
                .email("john.doe@example.com")
                .password("encodedPassword")
                .cellphone("123456789")
                .role(Role.ROLE_CUSTOMER)
                .street("Street")
                .number(123)
                .complement("Apt 101")
                .cep("12345-678")
                .district("District")
                .city("City")
                .cnpjOwner(null)
                .roleEmployee(null)
                .disponibilityStatusEmployee(null)
                .pets(new ArrayList<>())
                .build();

        when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));

        UserResponse user = userService.findUserById(1);

        assertNotNull(user);
        assertEquals("John Doe", user.getName());
    }

    @Test
    void testFindUserByIdNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.findUserById(1));
    }

    @Test
    void testGetAllUsersById() {
        List<User> users = Arrays.asList(User.builder()
                .id(1)
                .name("John Doe")
                .userImg("userImg.jpg")
                .email("john.doe@example.com")
                .password("encodedPassword")
                .cellphone("123456789")
                .role(Role.ROLE_CUSTOMER)
                .street("Street")
                .number(123)
                .complement("Apt 101")
                .cep("12345-678")
                .district("District")
                .city("City")
                .cnpjOwner(null)
                .roleEmployee(null)
                .disponibilityStatusEmployee(null)
                .pets(new ArrayList<>())
                .build());

        when(userRepository.findAllByDeletedAtIsNull()).thenReturn(users);

        List<UserResponse> userList = userService.findAllUsersById();

        assertNotNull(userList);
        assertEquals(1, userList.size());
        assertEquals("John Doe", userList.get(0).getName());
    }

    @Test
    void testGenerateCsvFileCustomerAndPets() {
        List<User> users = Arrays.asList(User.builder()
                .id(1)
                .name("John Doe")
                .userImg("userImg.jpg")
                .email("john.doe@example.com")
                .password("encodedPassword")
                .cellphone("123456789")
                .role(Role.ROLE_CUSTOMER)
                .street("Street")
                .number(123)
                .complement("Apt 101")
                .cep("12345-678")
                .district("District")
                .city("City")
                .cnpjOwner(null)
                .roleEmployee(null)
                .disponibilityStatusEmployee(null)
                .pets(new ArrayList<>())
                .build());

        when(userRepository.findAllByDeletedAtIsNull()).thenReturn(users);

        byte[] csvFile = userService.generateCsvFileCustomerAndPets();

        assertNotNull(csvFile);
        assertTrue(csvFile.length > 0);
    }

    @Test
    void testGetAllCustomers() {
        List<User> users = Arrays.asList(User.builder()
                .id(1)
                .name("John Doe")
                .userImg("userImg.jpg")
                .email("john.doe@example.com")
                .password("encodedPassword")
                .cellphone("123456789")
                .role(Role.ROLE_CUSTOMER)
                .street("Street")
                .number(123)
                .complement("Apt 101")
                .cep("12345-678")
                .district("District")
                .city("City")
                .cnpjOwner(null)
                .roleEmployee(null)
                .disponibilityStatusEmployee(null)
                .pets(new ArrayList<>())
                .build());

        when(userRepository.findByRoleAndDeletedAtIsNull(Role.ROLE_CUSTOMER)).thenReturn(users);
        when(petServiceImpl.maptoPetPetsListResponse(any(List.class)))
                .thenReturn(new ArrayList<PetPetsListResponse>());
        List<UserCustomerResponse> customerList = userService.getAllCustomers();

        assertNotNull(customerList);
        assertEquals(1, customerList.size());
        assertEquals("John Doe", customerList.get(0).getName());
    }
}
