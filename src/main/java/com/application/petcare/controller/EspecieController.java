package com.application.petcare.controller;


import com.application.petcare.dto.especie.EspecieCreateRequest;
import com.application.petcare.dto.especie.EspecieResponse;
import com.application.petcare.services.EspecieService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/especies")
@RequiredArgsConstructor
@Tag(name = "Especie", description = "Gerenciar especies de pets")
public class EspecieController {

    private final EspecieService especieService;

    @Operation(summary = "Criar uma nova espécie", description = "Cria uma nova espécie com os dados fornecidos.")
    @PostMapping
    public ResponseEntity<EspecieResponse> createEspecie(@Valid @RequestBody EspecieCreateRequest request) {
        return ResponseEntity.ok(especieService.createEspecie(request));
    }

    @Operation(summary = "Obter uma espécie pelo ID", description = "Retorna os detalhes de uma espécie específica pelo ID.")
    @GetMapping("/{id}")
    public ResponseEntity<EspecieResponse> getEspecieById(@PathVariable UUID id) {
        return ResponseEntity.ok(especieService.getEspecieById(id));
    }

    @Operation(summary = "Listar todas as espécies", description = "Retorna uma lista de todas as espécies cadastradas.")
    @GetMapping
    public ResponseEntity<List<EspecieResponse>> getAllEspecies() {
        return ResponseEntity.ok(especieService.getAllEspecies());
    }

    @Operation(summary = "Atualizar uma espécie existente", description = "Atualiza os dados de uma espécie existente pelo ID.")
    @PutMapping("/{id}")
    public ResponseEntity<EspecieResponse> updateEspecie(
            @PathVariable UUID id,
            @Valid @RequestBody EspecieCreateRequest request) {
        return ResponseEntity.ok(especieService.updateEspecie(id, request));
    }

    @Operation(summary = "Deletar uma espécie pelo ID", description = "Deleta uma espécie existente pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content, quando a espécie é deletada com sucesso")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEspecie(@PathVariable UUID id) {
        especieService.deleteEspecie(id);
        return ResponseEntity.noContent().build();
    }
}
