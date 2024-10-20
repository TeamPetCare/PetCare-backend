package com.application.petcare.services;

import com.application.petcare.dto.specie.SpecieCreateRequest;
import com.application.petcare.dto.specie.SpecieResponse;

import java.util.List;

public interface SpecieService {

    SpecieResponse createEspecie(SpecieCreateRequest request);

    SpecieResponse getEspecieById(Integer id);

    List<SpecieResponse> getAllEspecies();

    SpecieResponse updateEspecie(Integer id, SpecieCreateRequest request);

    void deleteEspecie(Integer id);
}