package com.application.petcare.controller.impl;

import com.application.petcare.controller.ServicesController;
import com.application.petcare.dto.services.ServicesCreateRequest;
import com.application.petcare.dto.services.ServicesResponse;
import com.application.petcare.dto.services.ServicesUpdateRequest;
import com.application.petcare.entities.Services;
import com.application.petcare.services.ServicesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ServicesControllerImpl implements ServicesController {
    private final ServicesService servicesService;

    @PostMapping
    public ResponseEntity<ServicesResponse> createService(@Valid @RequestBody ServicesCreateRequest request){
        return ResponseEntity.status(201).body(servicesService.createService(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicesResponse> getServiceById(Integer id) {
        return ResponseEntity.ok(servicesService.getServiceById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicesResponse> updateService
            (@PathVariable Integer id,
             @Valid @RequestBody ServicesUpdateRequest request){
        return ResponseEntity.ok(servicesService.updateService(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Integer id){
        servicesService.deleteService(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<ServicesResponse>> getServicesByIdsList(List<Integer> ids) {
        return ResponseEntity.ok().body(servicesService.getServicesByIdsList(ids));
    }

    @GetMapping
    public ResponseEntity<List<ServicesResponse>> getAllServices(){
        List<ServicesResponse> servicos = servicesService.findAllServices();
        return ResponseEntity.status(HttpStatus.OK).body(servicos);
    }


}
