package com.application.petcare.services;

import com.application.petcare.dto.size.SizeCreateRequest;
import com.application.petcare.dto.size.SizeResponse;

import java.util.List;
import java.util.UUID;

public interface SizeService {
    SizeResponse createSize(SizeCreateRequest request);
    SizeResponse updateSize(UUID id, SizeCreateRequest request);
    void deleteSize(UUID id);
    SizeResponse getSizeById(UUID id);
    List<SizeResponse> getAllSizes();
}
