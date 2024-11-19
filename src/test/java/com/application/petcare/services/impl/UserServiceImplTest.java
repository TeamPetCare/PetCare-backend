package com.application.petcare.services.impl;

import com.application.petcare.dto.user.UserCreateRequest;
import com.application.petcare.dto.user.UserUpdateRequest;
import com.application.petcare.dto.user.UserResponse;
import com.application.petcare.entities.User;
import com.application.petcare.enums.Role;
import com.application.petcare.exceptions.DuplicateEntryFoundException;
import com.application.petcare.exceptions.ResourceNotFoundException;
import com.application.petcare.repository.PetRepository;
import com.application.petcare.repository.UserRepository;
import com.application.petcare.utils.ImageDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ImageDatabase imageDatabase;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve criar um usuário com sucesso")
    void createUser_shouldCreateAndReturnUserResponse() {
        // Arrange
        UserCreateRequest request = UserCreateRequest.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .password("password123")
                .role(Role.ROLE_CUSTOMER)
                .street("Main St")
                .number(123)
                .cep("12345678")
                .district("Downtown")
                .city("Springfield")
                .build();

        User user = User.builder()
                .id(1)
                .name(request.getName())
                .email(request.getEmail())
                .password("encodedPassword")
                .role(request.getRole())
                .build();

        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        UserResponse response = userService.createUser(request);

        // Assert
        assertNotNull(response);
        assertEquals("John Doe", response.getName());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar criar usuário com e-mail duplicado")
    void createUser_shouldThrowDuplicateEntryFoundException() {
        // Arrange
        UserCreateRequest request = UserCreateRequest.builder()
                .email("existing@example.com")
                .build();

        User existingUser = User.builder().email("existing@example.com").build();

        when(userRepository.findByEmail("existing@example.com"))
                .thenReturn(Optional.of(existingUser));

        // Act & Assert
        assertThrows(DuplicateEntryFoundException.class, () -> userService.createUser(request));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    @DisplayName("Deve retornar um usuário pelo ID com sucesso")
    void findUserById_shouldReturnUserResponse() {
        // Arrange
        User user = User.builder()
                .id(1)
                .name("Jane Doe")
                .email("jane.doe@example.com")
                .build();

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        // Act
        UserResponse response = userService.findUserById(1);

        // Assert
        assertNotNull(response);
        assertEquals("Jane Doe", response.getName());
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar usuário inexistente pelo ID")
    void findUserById_shouldThrowResourceNotFoundException() {
        // Arrange
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> userService.findUserById(1));
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Deve deletar um usuário com sucesso")
    void deleteUser_shouldDeleteUser() {
        // Arrange
        when(userRepository.existsById(1)).thenReturn(true);

        // Act
        userService.deleteUser(1);

        // Assert
        verify(userRepository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar deletar usuário inexistente")
    void deleteUser_shouldThrowResourceNotFoundException() {
        // Arrange
        when(userRepository.existsById(1)).thenReturn(false);

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(1));
        verify(userRepository, never()).deleteById(1);
    }

    @Test
    @DisplayName("Deve atualizar um usuário com sucesso")
    void updateUser_shouldUpdateAndReturnUserResponse() {
        // Arrange
        User user = User.builder()
                .id(1)
                .name("John Doe")
                .email("john.doe@example.com")
                .build();

        User updatedUser = User.builder()
                .id(1)
                .name("John Updated")
                .email("john.updated@example.com")
                .build();

        UserUpdateRequest updateRequest = UserUpdateRequest.builder()
                .name("John Updated")
                .email("john.updated@example.com")
                .password("newPassword123")
                .cellphone("1234567890")
                .role(Role.ROLE_CUSTOMER)
                .street("Updated St")
                .number(456)
                .cep("87654321")
                .district("Uptown")
                .city("New Springfield")
                .build();

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode(updateRequest.getPassword())).thenReturn("encodedNewPassword");
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        // Act
        UserResponse response = userService.updateUser(1, updateRequest);

        // Assert
        assertNotNull(response);
        assertEquals("John Updated", response.getName());
        assertEquals("john.updated@example.com", response.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }
}
