package com.application.petcare.services;

import com.application.petcare.dto.especie.EspecieCreateRequest;
import com.application.petcare.dto.especie.EspecieResponse;

import java.util.List;

public interface EspecieService {

    EspecieResponse createEspecie(EspecieCreateRequest request);

    EspecieResponse getEspecieById(Integer id);

    List<EspecieResponse> getAllEspecies();

    EspecieResponse updateEspecie(Integer id, EspecieCreateRequest request);

    void deleteEspecie(Integer id);
}