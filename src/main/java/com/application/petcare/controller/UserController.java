package com.application.petcare.controller;

import com.application.petcare.dto.user.*;
import com.application.petcare.entities.User;
import com.azure.core.management.Resource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User", description = "Gerenciar usuários")
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
    @GetMapping(value = "/reportCustumersAndPets", produces = "text/csv")
    ResponseEntity<byte[]> generateCsvFileCustomerAndPets();

    @Operation(summary = "Atualizar usuário por ID")
    @PutMapping("/{id}")
    ResponseEntity<UserResponse> updateUser(
            @PathVariable Integer id,
            @RequestBody UserUpdateRequest userUpdateRequest);

    @Operation(summary = "Atualizar cliente por ID")
    @PutMapping("/customers/{id}")
    ResponseEntity<UserResponse> updateCustomer(@PathVariable Integer id, @RequestBody UserCustomerUpdateRequest request);


    @Operation(summary = "Excluir usuário por ID")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable Integer id);



    @Operation(summary = "Buscar todos os clientes")
    @GetMapping("/customers")
    ResponseEntity<List<UserCustomerResponse>> getAllCustomers();

    @Operation(summary = "Buscar todos os clientes ordenados por nome")
    @GetMapping("/customers/name")
    ResponseEntity<List<UserCustomerResponse>> getAllCustomersSortedByName();

    @Operation(summary = "Buscar todos os funcionarios")
    @GetMapping("/employees")
    ResponseEntity<List<UserEmployeeResponse>> getAllEmployees();

    @Operation(summary = "Deleta muitos clientes por ids")
    @DeleteMapping("/customers/delete")
    ResponseEntity<Void> deleteSelectedCustomers(@RequestBody List<UserCustomerDeleteRequest> userCustomerDeleteRequests);

    @Operation(summary = "Pega as informacoes do usuario")
    @GetMapping("/info/{id}")
    ResponseEntity<UserInfosResponse> getUserInfo(@PathVariable Integer id);
}


