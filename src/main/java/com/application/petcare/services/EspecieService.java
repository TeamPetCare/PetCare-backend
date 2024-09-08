package com.application.petcare.services;

import com.application.petcare.dto.especie.EspecieCreateRequest;
import com.application.petcare.dto.especie.EspecieResponse;

import java.util.List;
import java.util.UUID;

public interface EspecieService {

    EspecieResponse createEspecie(EspecieCreateRequest request);

    EspecieResponse getEspecieById(UUID id);

    List<EspecieResponse> getAllEspecies();

    EspecieResponse updateEspecie(UUID id, EspecieCreateRequest request);

    void deleteEspecie(UUID id);
}