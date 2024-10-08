package com.application.petcare.services;

import com.application.petcare.dto.raca.RacaCreateRequest;
import com.application.petcare.dto.raca.RacaResponse;

import java.util.List;

public interface RacaService {
    RacaResponse createRaca(RacaCreateRequest request);
    RacaResponse updateRaca(Integer id, RacaCreateRequest request);
    RacaResponse getRacaById(Integer id);
    List<RacaResponse> getAllRacas();
    void deleteRaca(Integer id);
}