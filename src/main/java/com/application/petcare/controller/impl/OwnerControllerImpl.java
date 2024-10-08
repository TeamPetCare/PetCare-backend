package com.application.petcare.controller.impl;

import com.application.petcare.controller.OwnerController;
import com.application.petcare.dto.owner.OwnerCreateRequest;
import com.application.petcare.dto.owner.OwnerResponse;
import com.application.petcare.dto.owner.OwnerUpdateRequest;
import com.application.petcare.services.OwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OwnerControllerImpl implements OwnerController {

    private final OwnerService ownerService;

    @Override
    public ResponseEntity<OwnerResponse> createOwner(@Valid OwnerCreateRequest request) {
        OwnerResponse response = ownerService.createOwner(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<OwnerResponse> getOwnerById(Integer id) {
        OwnerResponse response = ownerService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OwnerResponse> updateOwner(Integer id, @Valid OwnerUpdateRequest request) {
        OwnerResponse response = ownerService.updateOwner(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteOwner(Integer id) {
        ownerService.deleteOwner(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<OwnerResponse>> getAllOwners() {
        List<OwnerResponse> responseList = ownerService.findAllOwners();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }
}
