package com.application.petcare.controller;

import com.application.petcare.dto.services.ServicesCreateRequest;
import com.application.petcare.dto.services.ServicesResponse;
import com.application.petcare.dto.services.ServicesUpdateRequest;
import com.application.petcare.services.ServicesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicos")
@RequiredArgsConstructor
public class ServicesController {
    private final ServicesService servicesService;

    @PostMapping
    public ResponseEntity<ServicesResponse> createServico(@Valid @RequestBody ServicesCreateRequest request){
        return ResponseEntity.ok(servicesService.createServico(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicesResponse> updateServico
            (@PathVariable Integer id,
             @Valid @RequestBody ServicesUpdateRequest request){
        return ResponseEntity.ok(servicesService.updateServico(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteServico(@PathVariable Integer id){
        servicesService.deleteServico(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ServicesResponse>> getAllServicos(){
        List<ServicesResponse> servicos = servicesService.findAllServicos();
        return ResponseEntity.status(HttpStatus.OK).body(servicos);
    }

}
