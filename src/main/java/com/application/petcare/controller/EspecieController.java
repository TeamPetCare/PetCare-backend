package com.application.petcare.controller;


import com.application.petcare.dto.especie.EspecieCreateRequest;
import com.application.petcare.dto.especie.EspecieResponse;
import com.application.petcare.services.EspecieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/especies")
@RequiredArgsConstructor
public class EspecieController {

    private final EspecieService especieService;

    @PostMapping
    public ResponseEntity<EspecieResponse> createEspecie( @Valid
            @RequestBody EspecieCreateRequest request){
        return ResponseEntity.ok(especieService.createEspecie(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecieResponse> getEspecieById(@PathVariable UUID id){
        return ResponseEntity.ok(especieService.getEspecieById(id));
    }

    @GetMapping
    public ResponseEntity<List<EspecieResponse>> getAllEspecies(){
        return ResponseEntity.ok(especieService.getAllEspecies());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspecieResponse> updateEspecie(@PathVariable UUID id, @Valid
    @RequestBody EspecieCreateRequest request){
        return ResponseEntity.ok(especieService.updateEspecie(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEspecie(@PathVariable UUID id){
        especieService.deleteEspecie(id);
        return ResponseEntity.noContent().build();
    }

}
