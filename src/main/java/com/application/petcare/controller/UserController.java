package com.application.petcare.controller;

import com.application.petcare.dto.user.UserCreateRequest;
import com.application.petcare.dto.user.UserResponse;
import com.application.petcare.dto.user.UserUpdateRequest;
import com.application.petcare.entities.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User", description = "Gerenciar funcionários")
@RequestMapping("/api/users")
public interface UserController {
    @Operation(summary = "Criar um novo usuário")
    @PostMapping
    ResponseEntity<UserResponse> createUser(@RequestBody UserCreateRequest userCreateRequest);

    @Operation(summary = "Buscar usuário por ID")
    @GetMapping("/{id}")
    ResponseEntity<UserResponse> getUserById(@PathVariable Integer id);

    @Operation(summary = "Buscar todos os usuários")
    @GetMapping
    ResponseEntity<List<UserResponse>> getAllUsers();

    @Operation(summary = "Gerar relatório CSV de Clientes")
    @GetMapping("/relatarioClientes")
    ResponseEntity<Void> generateCsvFileCustomer();

    @Operation(summary = "Atualizar usuário por ID")
    @PutMapping("/{id}")
    ResponseEntity<UserResponse> updateUser(
            @PathVariable Integer id,
            @RequestBody UserUpdateRequest userUpdateRequest);

    @Operation(summary = "Excluir usuário por ID")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable Integer id);

    @Operation(summary = "Buscar todos os clientes")
    @GetMapping("/customers")
    ResponseEntity<List<User>> getAllCustomers();
}
