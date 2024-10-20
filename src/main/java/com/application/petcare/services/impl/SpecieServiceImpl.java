package com.application.petcare.services.impl;

import com.application.petcare.dto.specie.SpecieCreateRequest;
import com.application.petcare.dto.specie.SpecieResponse;
import com.application.petcare.entities.Specie;
import com.application.petcare.exceptions.EspecieNotFoundException;
import com.application.petcare.repository.SpecieRepository;
import com.application.petcare.services.SpecieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SpecieServiceImpl implements SpecieService {

    private final SpecieRepository especieRepository;

    @Override
    public SpecieResponse createEspecie(SpecieCreateRequest request) {
        log.info("Creating species: {}", request);

        Specie specie = Specie.builder()
                .name(request.getName())
                .priece(request.getPriece())
                .build();

        Specie savedSpecie = especieRepository.save(specie);
        log.info("Species created successfully: {}", savedSpecie);
        return mapToEspecieResponse(savedSpecie);
    }

    @Override
    public SpecieResponse getEspecieById(Integer id) {
        log.info("Fetching species by id: {}", id);

        Specie specie = especieRepository.findById(id)
                .orElseThrow(() -> new EspecieNotFoundException("Espécie não encontrada!"));
        return mapToEspecieResponse(specie);
    }

    @Override
    public List<SpecieResponse> getAllEspecies() {
        log.info("Fetching all species");

        return especieRepository.findAll().stream()
                .map(this::mapToEspecieResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SpecieResponse updateEspecie(Integer id, SpecieCreateRequest request) {
        log.info("Updating species with id: {}", id);

        Specie specie = especieRepository.findById(id)
                .orElseThrow(() -> new EspecieNotFoundException("Espécie não encontrada!"));

        specie.setName(request.getName());
        specie.setPriece(request.getPriece());

        Specie updatedSpecie = especieRepository.save(specie);
        log.info("Species updated successfully: {}", updatedSpecie);
        return mapToEspecieResponse(updatedSpecie);
    }

    @Override
    public void deleteEspecie(Integer id) {
        log.info("Deleting species with id: {}", id);

        if (!especieRepository.existsById(id)) {
            throw new EspecieNotFoundException("Espécie não encontrada!");
        }
        especieRepository.deleteById(id);
        log.info("Species deleted successfully with id: {}", id);
    }

    private SpecieResponse mapToEspecieResponse(Specie specie) {
        return new SpecieResponse(specie.getId(),
                specie.getName(),
                specie.getPriece());
    }
}
