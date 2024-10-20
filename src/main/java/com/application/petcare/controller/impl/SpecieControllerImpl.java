package com.application.petcare.controller.impl;

import com.application.petcare.controller.SpecieController;
import com.application.petcare.dto.specie.SpecieCreateRequest;
import com.application.petcare.dto.specie.SpecieResponse;
import com.application.petcare.services.SpecieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SpecieControllerImpl implements SpecieController {

    private final SpecieService specieService;

    @Override
    public ResponseEntity<SpecieResponse> createEspecie(@Valid SpecieCreateRequest request) {
        return ResponseEntity.ok(specieService.createEspecie(request));
    }

    @Override
    public ResponseEntity<SpecieResponse> getEspecieById(Integer id) {
        return ResponseEntity.ok(specieService.getEspecieById(id));
    }

    @Override
    public ResponseEntity<List<SpecieResponse>> getAllEspecies() {
        return ResponseEntity.ok(specieService.getAllEspecies());
    }

    @Override
    public ResponseEntity<SpecieResponse> updateEspecie(Integer id, @Valid SpecieCreateRequest request) {
        return ResponseEntity.ok(specieService.updateEspecie(id, request));
    }

    @Override
    public ResponseEntity<Void> deleteEspecie(Integer id) {
        specieService.deleteEspecie(id);
        return ResponseEntity.noContent().build();
    }
}
