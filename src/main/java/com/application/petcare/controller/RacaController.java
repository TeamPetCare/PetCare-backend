package com.application.petcare.controller;

import com.application.petcare.dto.raca.RacaCreateRequest;
import com.application.petcare.dto.raca.RacaResponse;
import com.application.petcare.services.RacaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/racas")
@RequiredArgsConstructor
public class RacaController {

    private final RacaService racaService;

    @PostMapping
    public ResponseEntity<RacaResponse> createRaca(@Valid @RequestBody RacaCreateRequest request) {
        return ResponseEntity.ok(racaService.createRaca(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RacaResponse> updateRaca(@PathVariable UUID id, @Valid @RequestBody RacaCreateRequest request) {
        return ResponseEntity.ok(racaService.updateRaca(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RacaResponse> getRacaById(@PathVariable UUID id) {
        return ResponseEntity.ok(racaService.getRacaById(id));
    }

    @GetMapping
    public ResponseEntity<List<RacaResponse>> getAllRacas() {
        return ResponseEntity.ok(racaService.getAllRacas());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRaca(@PathVariable UUID id) {
        racaService.deleteRaca(id);
        return ResponseEntity.noContent().build();
    }
}