package com.application.petcare.services;

import com.application.petcare.dto.race.RaceCreateRequest;
import com.application.petcare.dto.race.RaceResponse;

import java.util.List;

public interface RaceService {
    RaceResponse createRace(RaceCreateRequest request);
    RaceResponse updateRace(Integer id, RaceCreateRequest request);
    RaceResponse getRaceById(Integer id);
    List<RaceResponse> getAllRaces();
    void deleteRace(Integer id);
}