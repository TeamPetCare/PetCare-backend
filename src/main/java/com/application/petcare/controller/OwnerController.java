package com.application.petcare.controller;

import com.application.petcare.dto.owner.OwnerCreateRequest;
import com.application.petcare.dto.owner.OwnerResponse;
import com.application.petcare.dto.owner.OwnerUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Owner", description = "Gerenciar Owner/admin")
@RequestMapping("/api/owners")
public interface OwnerController {

    @Operation(summary = "Criar um novo owner", description = "Cria um novo owner com os dados fornecidos.")
    @PostMapping
    ResponseEntity<OwnerResponse> createOwner(@Valid @RequestBody OwnerCreateRequest request);

    @Operation(summary = "Obter um owner pelo ID", description = "Retorna os detalhes de um owner específico pelo ID.")
    @GetMapping("/{id}")
    ResponseEntity<OwnerResponse> getOwnerById(@PathVariable Integer id);

    @Operation(summary = "Atualizar um owner existente", description = "Atualiza os dados de um owner existente pelo ID.")
    @PutMapping("/{id}")
    ResponseEntity<OwnerResponse> updateOwner(@PathVariable Integer id, @Valid @RequestBody OwnerUpdateRequest request);

    @Operation(summary = "Deletar um owner pelo ID", description = "Deleta um owner existente pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content, quando o owner é deletado com sucesso")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteOwner(@PathVariable Integer id);

    @Operation(summary = "Listar todos os owners", description = "Retorna uma lista de todos os owners cadastrados.")
    @GetMapping
    ResponseEntity<List<OwnerResponse>> getAllOwners();
}
