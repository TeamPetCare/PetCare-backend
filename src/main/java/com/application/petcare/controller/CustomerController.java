package com.application.petcare.controller;

import com.application.petcare.dto.customer.CustomerCreateRequest;
import com.application.petcare.dto.customer.CustomerResponse;
import com.application.petcare.dto.customer.CustomerUpdateRequest;
import com.application.petcare.enums.Role;
import com.application.petcare.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/customers")
@RequiredArgsConstructor
@Tag(name = "Customer", description = "Gerenciar clientes")
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "Criar um novo cliente", description = "Cria um novo cliente no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(
            @RequestBody @Parameter(description = "Detalhes para o novo cliente") CustomerCreateRequest customerCreateRequest) {
        CustomerResponse createdCustomer = customerService.createCustomer(customerCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @Operation(summary = "Buscar cliente por ID", description = "Busca um cliente através do seu ID único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(
            @PathVariable @Parameter(description = "ID único do cliente") UUID id) {
        CustomerResponse customerResponse = customerService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(customerResponse);
    }

    @Operation(summary = "Listar todos os clientes", description = "Retorna uma lista de todos os clientes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        List<CustomerResponse> customers = customerService.getAllCustomers();
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @Operation(summary = "Atualizar cliente", description = "Atualiza os dados de um cliente existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @PathVariable @Parameter(description = "ID único do cliente a ser atualizado") UUID id,
            @RequestBody @Parameter(description = "Dados atualizados do cliente") CustomerUpdateRequest customerUpdateRequest) {
        CustomerResponse updatedCustomer = customerService.updateCustomer(id, customerUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCustomer);
    }

    @Operation(summary = "Deletar cliente", description = "Remove um cliente através do seu ID único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable @Parameter(description = "ID único do cliente a ser removido") UUID id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
