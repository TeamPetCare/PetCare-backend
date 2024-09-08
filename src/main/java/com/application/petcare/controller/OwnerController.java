package com.application.petcare.controller;

import com.application.petcare.dto.owner.OwnerCreateRequest;
import com.application.petcare.dto.owner.OwnerResponse;
import com.application.petcare.dto.owner.OwnerUpdateRequest;
import com.application.petcare.services.OwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/owners")
public class OwnerController {
    private final OwnerService ownerService;

    @PostMapping
    public ResponseEntity<OwnerResponse> createOwner(@Valid @RequestBody OwnerCreateRequest request){
        OwnerResponse response = ownerService.createOwner(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponse> getOwnerById(@PathVariable UUID id){

        OwnerResponse response = ownerService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerResponse> updateOwner(
            @PathVariable UUID id,
            @RequestBody OwnerUpdateRequest request){
        OwnerResponse response = ownerService.updateOwner(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OwnerResponse> deleteOwner(@PathVariable UUID id){
        ownerService.deleteOwner(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<OwnerResponse>> getAllOwners(){
        List<OwnerResponse> responseList = ownerService.findAllOwners();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

}
