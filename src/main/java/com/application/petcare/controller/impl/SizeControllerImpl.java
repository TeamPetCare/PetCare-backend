package com.application.petcare.controller.impl;

import com.application.petcare.controller.SizeController;
import com.application.petcare.dto.size.SizeCreateRequest;
import com.application.petcare.dto.size.SizeResponse;
import com.application.petcare.services.SizeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class SizeControllerImpl implements SizeController {

    private final SizeService sizeService;

    @Override
    public ResponseEntity<SizeResponse> createSize(@Valid SizeCreateRequest request) {
        return ResponseEntity.ok(sizeService.createSize(request));
    }

    @Override
    public ResponseEntity<SizeResponse> updateSize(UUID id, @Valid SizeCreateRequest request) {
        return ResponseEntity.ok(sizeService.updateSize(id, request));
    }

    @Override
    public ResponseEntity<Void> deleteSize(UUID id) {
        sizeService.deleteSize(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<SizeResponse> getSizeById(UUID id) {
        return ResponseEntity.ok(sizeService.getSizeById(id));
    }

    @Override
    public ResponseEntity<List<SizeResponse>> getAllSizes() {
        return ResponseEntity.ok(sizeService.getAllSizes());
    }
}
