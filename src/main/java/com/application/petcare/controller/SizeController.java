package com.application.petcare.controller;

import com.application.petcare.dto.size.SizeCreateRequest;
import com.application.petcare.dto.size.SizeResponse;
import com.application.petcare.services.SizeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/sizes")
@RequiredArgsConstructor
@Tag(name = "Size", description = "API para gerenciar tamanhos de pets")
public class SizeController {

    private final SizeService sizeService;

    @Operation(summary = "Criar um novo tamanho")
    @PostMapping
    public ResponseEntity<SizeResponse> createSize(@Valid @RequestBody SizeCreateRequest request) {
        return ResponseEntity.ok(sizeService.createSize(request));
    }

    @Operation(summary = "Atualizar um tamanho existente")
    @PutMapping("/{id}")
    public ResponseEntity<SizeResponse> updateSize(@PathVariable UUID id, @Valid @RequestBody SizeCreateRequest request) {
        return ResponseEntity.ok(sizeService.updateSize(id, request));
    }

    @Operation(summary = "Deletar um tamanho pelo ID", description = "Deleta um tamanho existente pelo ID.")
    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204", description = "Tamanho é deletado com sucesso")
    public ResponseEntity<Void> deleteSize(@PathVariable UUID id) {
        sizeService.deleteSize(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Buscar um tamanho pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<SizeResponse> getSizeById(@PathVariable UUID id) {
        return ResponseEntity.ok(sizeService.getSizeById(id));
    }

    @Operation(summary = "Listar todos os tamanhos")
    @GetMapping
    public ResponseEntity<List<SizeResponse>> getAllSizes() {
        return ResponseEntity.ok(sizeService.getAllSizes());
    }
}
