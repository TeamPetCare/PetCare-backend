package com.application.petcare.controller;

import com.application.petcare.dto.owner.OwnerCreateRequest;
import com.application.petcare.dto.owner.OwnerResponse;
import com.application.petcare.dto.owner.OwnerUpdateRequest;
import com.application.petcare.services.OwnerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/owners")
@Tag(name = "Owner", description = "Gerenciar Owner/admin")
public class OwnerController {
    private final OwnerService ownerService;

    @Operation(summary = "Criar um novo owner", description = "Cria um novo owner com os dados fornecidos.")
    @PostMapping
    public ResponseEntity<OwnerResponse> createOwner(@Valid @RequestBody OwnerCreateRequest request){
        OwnerResponse response = ownerService.createOwner(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Obter um owner pelo ID", description = "Retorna os detalhes de um owner específico pelo ID.")
    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponse> getOwnerById(@PathVariable UUID id){
        OwnerResponse response = ownerService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Atualizar um owner existente", description = "Atualiza os dados de um owner existente pelo ID.")
    @PutMapping("/{id}")
    public ResponseEntity<OwnerResponse> updateOwner(
            @PathVariable UUID id,
            @Valid @RequestBody OwnerUpdateRequest request){
        OwnerResponse response = ownerService.updateOwner(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Deletar um owner pelo ID", description = "Deleta um owner existente pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content, quando o owner é deletado com sucesso")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable UUID id){
        ownerService.deleteOwner(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Listar todos os owners", description = "Retorna uma lista de todos os owners cadastrados.")
    @GetMapping
    public ResponseEntity<List<OwnerResponse>> getAllOwners(){
        List<OwnerResponse> responseList = ownerService.findAllOwners();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }
}
