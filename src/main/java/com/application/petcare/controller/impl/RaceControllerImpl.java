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
    public ResponseEntity<RaceResponse> createRace(@Valid RaceCreateRequest request) {
        return ResponseEntity.ok(raceService.createRace(request));
    }

    @Override
    public ResponseEntity<RaceResponse> updateRace(Integer id, @Valid RaceCreateRequest request) {
        return ResponseEntity.ok(raceService.updateRace(id, request));
    }

    @Override
    public ResponseEntity<RaceResponse> getRaceById(Integer id) {
        return ResponseEntity.ok(raceService.getRaceById(id));
    }

    @Override
    public ResponseEntity<List<RaceResponse>> getAllRaces() {
        return ResponseEntity.ok(raceService.getAllRaces());
    }

    @Override
    public ResponseEntity<Void> deleteRace(Integer id) {
        raceService.deleteRace(id);
        return ResponseEntity.noContent().build();
    }
}
