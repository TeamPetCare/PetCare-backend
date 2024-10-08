package com.application.petcare.services;

import com.application.petcare.dto.size.SizeCreateRequest;
import com.application.petcare.dto.size.SizeResponse;

import java.util.List;

public interface SizeService {
    SizeResponse createSize(SizeCreateRequest request);
    SizeResponse updateSize(Integer id, SizeCreateRequest request);
    void deleteSize(Integer id);
    SizeResponse getSizeById(Integer id);
    List<SizeResponse> getAllSizes();
}
