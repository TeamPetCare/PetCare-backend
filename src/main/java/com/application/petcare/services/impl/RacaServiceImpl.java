package com.application.petcare.services.impl;

import com.application.petcare.dto.raca.RacaCreateRequest;
import com.application.petcare.dto.raca.RacaResponse;
import com.application.petcare.entities.Raca;
import com.application.petcare.exceptions.BadRequestException;
import com.application.petcare.exceptions.RacaNotFoundException;
import com.application.petcare.repository.RacaRepository;
import com.application.petcare.services.RacaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RacaServiceImpl implements RacaService {

    private final RacaRepository racaRepository;

    @Override
    public RacaResponse createRaca(RacaCreateRequest request) {
        log.info("Creating raca: {}", request);

        Raca raca = Raca.builder()
                .tipoRaca(request.getTipoRaca())
                .preco(request.getPreco())
                .build();

        Raca savedRaca = racaRepository.save(raca);
        log.info("Raca created successfully: {}", savedRaca);
        return mapToRacaResponse(savedRaca);
    }

    @Override
    public RacaResponse updateRaca(Integer id, RacaCreateRequest request) {
        log.info("Updating raca with id: {}", id);

        Raca raca = racaRepository.findById(id)
                .orElseThrow(() -> new RacaNotFoundException("Raça não encontrada"));

        raca.setTipoRaca(request.getTipoRaca());
        raca.setPreco(request.getPreco());
        Raca updatedRaca = racaRepository.save(raca);
        log.info("Raca updated successfully: {}", updatedRaca);
        return mapToRacaResponse(updatedRaca);
    }

    @Override
    public RacaResponse getRacaById(Integer id) {
        log.info("Fetching raca by id: {}", id);

        Raca raca = racaRepository.findById(id)
                .orElseThrow(() -> new RacaNotFoundException("Raça não encontrada"));
        return mapToRacaResponse(raca);
    }

    @Override
    public List<RacaResponse> getAllRacas() {
        log.info("Fetching all racas");

        return racaRepository.findAll().stream()
                .map(this::mapToRacaResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRaca(Integer id) {
        log.info("Deleting raca with id: {}", id);

        if (!racaRepository.existsById(id)) {
            throw new RacaNotFoundException("Raça não encontrada");
        }
        racaRepository.deleteById(id);
        log.info("Raca deleted successfully with id: {}", id);
    }

    private RacaResponse mapToRacaResponse(Raca raca) {
        return new RacaResponse(
                raca.getId(),
                raca.getTipoRaca(),
                raca.getPreco()
        );
    }
}
