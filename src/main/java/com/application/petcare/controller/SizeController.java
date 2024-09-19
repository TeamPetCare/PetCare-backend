package com.application.petcare.controller;

import com.application.petcare.dto.size.SizeCreateRequest;
import com.application.petcare.dto.size.SizeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@Tag(name = "Size", description = "Gerenciar tamanhos de pets")
@RequestMapping("/api/sizes")
public interface SizeController {

    @Operation(summary = "Criar um novo tamanho")
    @PostMapping
    ResponseEntity<SizeResponse> createSize(@Valid @RequestBody SizeCreateRequest request);

    @Operation(summary = "Atualizar um tamanho existente")
    @PutMapping("/{id}")
    ResponseEntity<SizeResponse> updateSize(@PathVariable UUID id, @Valid @RequestBody SizeCreateRequest request);

    @Operation(summary = "Deletar um tamanho pelo ID", description = "Deleta um tamanho existente pelo ID.")
    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204", description = "Tamanho é deletado com sucesso")
    ResponseEntity<Void> deleteSize(@PathVariable UUID id);

    @Operation(summary = "Buscar um tamanho pelo ID")
    @GetMapping("/{id}")
    ResponseEntity<SizeResponse> getSizeById(@PathVariable UUID id);

    @Operation(summary = "Listar todos os tamanhos")
    @GetMapping
    ResponseEntity<List<SizeResponse>> getAllSizes();
}
