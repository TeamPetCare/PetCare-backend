package com.application.petcare.services;

import com.application.petcare.dto.owner.OwnerCreateRequest;
import com.application.petcare.dto.owner.OwnerResponse;
import com.application.petcare.dto.owner.OwnerUpdateRequest;

import java.util.List;
import java.util.UUID;

public interface OwnerService {
    OwnerResponse createOwner(OwnerCreateRequest request);

    OwnerResponse findById(UUID id);

    OwnerResponse updateOwner(UUID id, OwnerUpdateRequest request);

    void deleteOwner(UUID id);

    List<OwnerResponse> findAllOwners();

}
