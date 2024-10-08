package com.application.petcare.services;

import com.application.petcare.dto.owner.OwnerCreateRequest;
import com.application.petcare.dto.owner.OwnerResponse;
import com.application.petcare.dto.owner.OwnerUpdateRequest;

import java.util.List;

public interface OwnerService {
    OwnerResponse createOwner(OwnerCreateRequest request);

    OwnerResponse findById(Integer id);

    OwnerResponse updateOwner(Integer id, OwnerUpdateRequest request);

    void deleteOwner(Integer id);

    List<OwnerResponse> findAllOwners();

}
