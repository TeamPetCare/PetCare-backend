package com.application.petcare.controller.impl;

import com.application.petcare.controller.EspecieController;
import com.application.petcare.dto.especie.EspecieCreateRequest;
import com.application.petcare.dto.especie.EspecieResponse;
import com.application.petcare.services.EspecieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EspecieControllerImpl implements EspecieController {

    private final EspecieService especieService;

    @Override
    public ResponseEntity<EspecieResponse> createEspecie(@Valid EspecieCreateRequest request) {
        return ResponseEntity.ok(especieService.createEspecie(request));
    }

    @Override
    public ResponseEntity<EspecieResponse> getEspecieById(Integer id) {
        return ResponseEntity.ok(especieService.getEspecieById(id));
    }

    @Override
    public ResponseEntity<List<EspecieResponse>> getAllEspecies() {
        return ResponseEntity.ok(especieService.getAllEspecies());
    }

    @Override
    public ResponseEntity<EspecieResponse> updateEspecie(Integer id, @Valid EspecieCreateRequest request) {
        return ResponseEntity.ok(especieService.updateEspecie(id, request));
    }

    @Override
    public ResponseEntity<Void> deleteEspecie(Integer id) {
        especieService.deleteEspecie(id);
        return ResponseEntity.noContent().build();
    }
}
