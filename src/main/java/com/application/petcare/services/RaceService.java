package com.application.petcare.services;

import com.application.petcare.dto.race.RaceCreateRequest;
import com.application.petcare.dto.race.RaceResponse;

import java.util.List;

public interface RaceService {
    RaceResponse createRaca(RaceCreateRequest request);
    RaceResponse updateRaca(Integer id, RaceCreateRequest request);
    RaceResponse getRacaById(Integer id);
    List<RaceResponse> getAllRacas();
    void deleteRaca(Integer id);
}