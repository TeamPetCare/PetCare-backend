package com.application.petcare.controller;

import com.application.petcare.dto.raca.RacaCreateRequest;
import com.application.petcare.dto.raca.RacaResponse;
import com.application.petcare.services.RacaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/racas")
@RequiredArgsConstructor
@Tag(name = "Raça", description = "Gerenciar raças de pets")
public class RacaController {

    private final RacaService racaService;

    @Operation(summary = "Criar uma nova raça", description = "Cria uma nova raça com os dados fornecidos.")
    @PostMapping
    public ResponseEntity<RacaResponse> createRaca(@Valid @RequestBody RacaCreateRequest request) {
        return ResponseEntity.ok(racaService.createRaca(request));
    }

    @Operation(summary = "Atualizar uma raça existente", description = "Atualiza os dados de uma raça existente pelo ID.")
    @PutMapping("/{id}")
    public ResponseEntity<RacaResponse> updateRaca(
            @PathVariable UUID id,
            @Valid @RequestBody RacaCreateRequest request) {
        return ResponseEntity.ok(racaService.updateRaca(id, request));
    }

    @Operation(summary = "Obter uma raça pelo ID", description = "Retorna os detalhes de uma raça específica pelo ID.")
    @GetMapping("/{id}")
    public ResponseEntity<RacaResponse> getRacaById(@PathVariable UUID id) {
        return ResponseEntity.ok(racaService.getRacaById(id));
    }

    @Operation(summary = "Listar todas as raças", description = "Retorna uma lista de todas as raças cadastradas.")
    @GetMapping
    public ResponseEntity<List<RacaResponse>> getAllRacas() {
        return ResponseEntity.ok(racaService.getAllRacas());
    }

    @Operation(summary = "Deletar uma raça pelo ID", description = "Deleta uma raça existente pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content, quando a raça é deletada com sucesso")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRaca(@PathVariable UUID id) {
        racaService.deleteRaca(id);
        return ResponseEntity.noContent().build();
    }
}
