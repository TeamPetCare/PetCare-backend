package com.application.petcare.controller.impl;

import com.application.petcare.controller.RaceController;
import com.application.petcare.dto.race.RaceCreateRequest;
import com.application.petcare.dto.race.RaceResponse;
import com.application.petcare.services.RaceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RaceControllerImpl implements RaceController {

    private final RaceService raceService;

    @Override
    public ResponseEntity<RaceResponse> createRaca(@Valid RaceCreateRequest request) {
        return ResponseEntity.ok(raceService.createRaca(request));
    }

    @Override
    public ResponseEntity<RaceResponse> updateRaca(Integer id, @Valid RaceCreateRequest request) {
        return ResponseEntity.ok(raceService.updateRaca(id, request));
    }

    @Override
    public ResponseEntity<RaceResponse> getRacaById(Integer id) {
        return ResponseEntity.ok(raceService.getRacaById(id));
    }

    @Override
    public ResponseEntity<List<RaceResponse>> getAllRacas() {
        return ResponseEntity.ok(raceService.getAllRacas());
    }

    @Override
    public ResponseEntity<Void> deleteRaca(Integer id) {
        raceService.deleteRaca(id);
        return ResponseEntity.noContent().build();
    }
}
