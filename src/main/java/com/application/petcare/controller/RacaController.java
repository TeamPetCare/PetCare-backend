package com.application.petcare.controller;

import com.application.petcare.dto.raca.RacaCreateRequest;
import com.application.petcare.dto.raca.RacaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Tag(name = "Raça", description = "Gerenciar raças de pets")
@RequestMapping("/api/racas")
public interface RacaController {

    @Operation(summary = "Criar uma nova raça", description = "Cria uma nova raça com os dados fornecidos.")
    @PostMapping
    ResponseEntity<RacaResponse> createRaca(@Valid @RequestBody RacaCreateRequest request);

    @Operation(summary = "Atualizar uma raça existente", description = "Atualiza os dados de uma raça existente pelo ID.")
    @PutMapping("/{id}")
    ResponseEntity<RacaResponse> updateRaca(@PathVariable Integer id, @Valid @RequestBody RacaCreateRequest request);

    @Operation(summary = "Obter uma raça pelo ID", description = "Retorna os detalhes de uma raça específica pelo ID.")
    @GetMapping("/{id}")
    ResponseEntity<RacaResponse> getRacaById(@PathVariable Integer id);

    @Operation(summary = "Listar todas as raças", description = "Retorna uma lista de todas as raças cadastradas.")
    @GetMapping
    ResponseEntity<List<RacaResponse>> getAllRacas();

    @Operation(summary = "Deletar uma raça pelo ID", description = "Deleta uma raça existente pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content, quando a raça é deletada com sucesso")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteRaca(@PathVariable Integer id);
}
