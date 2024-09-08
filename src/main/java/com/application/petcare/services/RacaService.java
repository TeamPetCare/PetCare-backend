package com.application.petcare.services;

import com.application.petcare.dto.raca.RacaCreateRequest;
import com.application.petcare.dto.raca.RacaResponse;

import java.util.List;
import java.util.UUID;

public interface RacaService {
    RacaResponse createRaca(RacaCreateRequest request);
    RacaResponse updateRaca(UUID id, RacaCreateRequest request);
    RacaResponse getRacaById(UUID id);
    List<RacaResponse> getAllRacas();
    void deleteRaca(UUID id);
}