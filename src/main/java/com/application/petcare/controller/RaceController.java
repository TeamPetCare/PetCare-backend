package com.application.petcare.controller;

import com.application.petcare.dto.race.RaceCreateRequest;
import com.application.petcare.dto.race.RaceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Tag(name = "Race", description = "Gerenciar raças de pets")
@RequestMapping("employee/api/races")
public interface RaceController {

    @Operation(summary = "Criar uma nova raça", description = "Cria uma nova raça com os dados fornecidos.")
    @PostMapping
    ResponseEntity<RaceResponse> createRace(@Valid @RequestBody RaceCreateRequest request);

    @Operation(summary = "Atualizar uma raça existente", description = "Atualiza os dados de uma raça existente pelo ID.")
    @PutMapping("/{id}")
    ResponseEntity<RaceResponse> updateRace(@PathVariable Integer id, @Valid @RequestBody RaceCreateRequest request);

    @Operation(summary = "Obter uma raça pelo ID", description = "Retorna os detalhes de uma raça específica pelo ID.")
    @GetMapping("/{id}")
    ResponseEntity<RaceResponse> getRaceById(@PathVariable Integer id);

    @Operation(summary = "Listar todas as raças", description = "Retorna uma lista de todas as raças cadastradas.")
    @GetMapping
    ResponseEntity<List<RaceResponse>> getAllRaces();

    @Operation(summary = "Deletar uma raça pelo ID", description = "Deleta uma raça existente pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content, quando a raça é deletada com sucesso")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteRace(@PathVariable Integer id);
}
