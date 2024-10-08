package com.application.petcare.controller;

import com.application.petcare.dto.especie.EspecieCreateRequest;
import com.application.petcare.dto.especie.EspecieResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Tag(name = "Especie", description = "Gerenciar especies de pets")
@RequestMapping("/api/especies")
public interface EspecieController {

    @Operation(summary = "Criar uma nova espécie", description = "Cria uma nova espécie com os dados fornecidos.")
    @PostMapping
    ResponseEntity<EspecieResponse> createEspecie(@Valid @RequestBody EspecieCreateRequest request);

    @Operation(summary = "Obter uma espécie pelo ID", description = "Retorna os detalhes de uma espécie específica pelo ID.")
    @GetMapping("/{id}")
    ResponseEntity<EspecieResponse> getEspecieById(@PathVariable Integer id);

    @Operation(summary = "Listar todas as espécies", description = "Retorna uma lista de todas as espécies cadastradas.")
    @GetMapping
    ResponseEntity<List<EspecieResponse>> getAllEspecies();

    @Operation(summary = "Atualizar uma espécie existente", description = "Atualiza os dados de uma espécie existente pelo ID.")
    @PutMapping("/{id}")
    ResponseEntity<EspecieResponse> updateEspecie(
            @PathVariable Integer id,
            @Valid @RequestBody EspecieCreateRequest request);

    @Operation(summary = "Deletar uma espécie pelo ID", description = "Deleta uma espécie existente pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content, quando a espécie é deletada com sucesso")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteEspecie(@PathVariable Integer id);
}
