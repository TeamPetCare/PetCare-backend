package com.application.petcare.controller.impl;

import com.application.petcare.controller.RacaController;
import com.application.petcare.dto.raca.RacaCreateRequest;
import com.application.petcare.dto.raca.RacaResponse;
import com.application.petcare.services.RacaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RacaControllerImpl implements RacaController {

    private final RacaService racaService;

    @Override
    public ResponseEntity<RacaResponse> createRaca(@Valid RacaCreateRequest request) {
        return ResponseEntity.ok(racaService.createRaca(request));
    }

    @Override
    public ResponseEntity<RacaResponse> updateRaca(Integer id, @Valid RacaCreateRequest request) {
        return ResponseEntity.ok(racaService.updateRaca(id, request));
    }

    @Override
    public ResponseEntity<RacaResponse> getRacaById(Integer id) {
        return ResponseEntity.ok(racaService.getRacaById(id));
    }

    @Override
    public ResponseEntity<List<RacaResponse>> getAllRacas() {
        return ResponseEntity.ok(racaService.getAllRacas());
    }

    @Override
    public ResponseEntity<Void> deleteRaca(Integer id) {
        racaService.deleteRaca(id);
        return ResponseEntity.noContent().build();
    }
}
