package com.application.petcare.services.impl;

import com.application.petcare.dto.race.RaceCreateRequest;
import com.application.petcare.dto.race.RaceResponse;
import com.application.petcare.entities.Race;
import com.application.petcare.exceptions.RaceNotFoundException;
import com.application.petcare.repository.RaceRepository;
import com.application.petcare.services.RaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RaceServiceImpl implements RaceService {

    private final RaceRepository raceRepository;

    @Override
    public RaceResponse createRace(RaceCreateRequest request) {
        log.info("Creating raca: {}", request);

        Race race = Race.builder()
                .raceType(request.getRaceType())
                .priece(request.getPriece())
                .build();

        Race savedRace = raceRepository.save(race);
        log.info("Raca created successfully: {}", savedRace);
        return mapToRacaResponse(savedRace);
    }

    @Override
    public RaceResponse updateRace(Integer id, RaceCreateRequest request) {
        log.info("Updating raca with id: {}", id);

        Race race = raceRepository.findById(id)
                .orElseThrow(() -> new RaceNotFoundException("Raça não encontrada"));

        race.setRaceType(request.getRaceType());
        race.setPriece(request.getPriece());
        Race updatedRace = raceRepository.save(race);
        log.info("Raca updated successfully: {}", updatedRace);
        return mapToRacaResponse(updatedRace);
    }

    @Override
    public RaceResponse getRaceById(Integer id) {
        log.info("Fetching raca by id: {}", id);

        Race race = raceRepository.findById(id)
                .orElseThrow(() -> new RaceNotFoundException("Raça não encontrada"));
        return mapToRacaResponse(race);
    }

    @Override
    public List<RaceResponse> getAllRaces() {
        log.info("Fetching all racas");

        return raceRepository.findAll().stream()
                .map(this::mapToRacaResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRace(Integer id) {
        log.info("Deleting raca with id: {}", id);

        if (!raceRepository.existsById(id)) {
            throw new RaceNotFoundException("Raça não encontrada");
        }
        raceRepository.deleteById(id);
        log.info("Raca deleted successfully with id: {}", id);
    }

    private RaceResponse mapToRacaResponse(Race race) {
        return new RaceResponse(
                race.getId(),
                race.getRaceType(),
                race.getPriece()
        );
    }
}