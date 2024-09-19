package com.application.petcare.controller;

import com.application.petcare.dto.customer.CustomerCreateRequest;
import com.application.petcare.dto.customer.CustomerResponse;
import com.application.petcare.dto.customer.CustomerUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Customer", description = "Gerenciar clientes")
public interface CustomerController {

    @Operation(summary = "Criar um novo cliente", description = "Cria um novo cliente no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    })
    @PostMapping
    ResponseEntity<CustomerResponse> createCustomer(
            @RequestBody @Parameter(description = "Detalhes para o novo cliente") CustomerCreateRequest customerCreateRequest);

    @Operation(summary = "Buscar cliente por ID", description = "Busca um cliente através do seu ID único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/{id}")
    ResponseEntity<CustomerResponse> getCustomerById(
            @PathVariable @Parameter(description = "ID único do cliente") UUID id);

    @Operation(summary = "Listar todos os clientes", description = "Retorna uma lista de todos os clientes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso")
    })
    @GetMapping
    ResponseEntity<List<CustomerResponse>> getAllCustomers();

    @Operation(summary = "Atualizar cliente", description = "Atualiza os dados de um cliente existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @PutMapping("/{id}")
    ResponseEntity<CustomerResponse> updateCustomer(
            @PathVariable @Parameter(description = "ID único do cliente a ser atualizado") UUID id,
            @RequestBody @Parameter(description = "Dados atualizados do cliente") CustomerUpdateRequest customerUpdateRequest);

    @Operation(summary = "Deletar cliente", description = "Remove um cliente através do seu ID único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCustomer(@PathVariable UUID id);
}
