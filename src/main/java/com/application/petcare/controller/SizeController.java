package com.application.petcare.controller;

import com.application.petcare.dto.size.SizeCreateRequest;
import com.application.petcare.dto.size.SizeResponse;
import com.application.petcare.services.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/sizes")
@RequiredArgsConstructor
public class SizeController {

    private final SizeService sizeService;

    @PostMapping
    public ResponseEntity<SizeResponse> createSize(@Valid @RequestBody SizeCreateRequest request) {
        return ResponseEntity.ok(sizeService.createSize(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SizeResponse> updateSize(@PathVariable UUID id, @Valid @RequestBody SizeCreateRequest request) {
        return ResponseEntity.ok(sizeService.updateSize(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSize(@PathVariable UUID id) {
        sizeService.deleteSize(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SizeResponse> getSizeById(@PathVariable UUID id) {
        return ResponseEntity.ok(sizeService.getSizeById(id));
    }

    @GetMapping
    public ResponseEntity<List<SizeResponse>> getAllSizes() {
        return ResponseEntity.ok(sizeService.getAllSizes());
    }
}