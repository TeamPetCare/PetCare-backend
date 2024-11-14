package com.application.petcare.services.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.application.petcare.dto.user.UserCreateRequest;
import com.application.petcare.dto.user.UserResponse;
import com.application.petcare.entities.User;
import com.application.petcare.enums.Role;
import com.application.petcare.exceptions.ResourceNotFoundException;
import com.application.petcare.repository.PetRepository;
import com.application.petcare.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1)
                .name("Daniela")
                .email("daniela@example.com")
                .password("encodedPassword")
                .role(Role.ROLE_CUSTOMER)
                .build();
    }

    @Test
    void testCreateUser_Success() {
        UserCreateRequest request = new UserCreateRequest();
        request.setName("Daniela");
        request.setEmail("daniela@example.com");
        request.setPassword("password");

        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponse response = userService.createUser(request);

        assertNotNull(response);
        assertEquals("Daniela", response.getName());
        assertEquals("daniela@example.com", response.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testFindUserById_UserExists() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        UserResponse response = userService.findUserById(1);

        assertNotNull(response);
        assertEquals("Daniela", response.getName());
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void testFindUserById_UserNotFound() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.findUserById(1));
    }

    @Test
    void testDeleteUser_Success() {
        when(userRepository.existsById(1)).thenReturn(true);
        doNothing().when(userRepository).deleteById(1);

        userService.deleteUser(1);

        verify(userRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteUser_UserNotFound() {
        when(userRepository.existsById(1)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(1));
        verify(userRepository, never()).deleteById(anyInt());
    }

    @Test
    void testUpdateUser_UserExists() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        user.setName("Daniela Updated");
        user.setEmail("updated@example.com");

        UserResponse response = userService.updateUser(1, new UserUpdateRequest("Daniela Updated", "updated@example.com"));

        assertNotNull(response);
        assertEquals("Daniela Updated", response.getName());
        assertEquals("updated@example.com", response.getEmail());
    }

    @Test
    void testUpdateUser_UserNotFound() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(1, new UserUpdateRequest()));
    }

    @Test
    void testGenerateCsvFileCustomerAndPets() {
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        assertDoesNotThrow(() -> userService.generateCsvFileCustomerAndPets());
    }
}
