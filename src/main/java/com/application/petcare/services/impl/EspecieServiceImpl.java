package com.application.petcare.services.impl;

import com.application.petcare.dto.especie.EspecieCreateRequest;
import com.application.petcare.dto.especie.EspecieResponse;
import com.application.petcare.entities.Especie;
import com.application.petcare.exceptions.EspecieNotFoundException;
import com.application.petcare.repository.EspecieRepository;
import com.application.petcare.services.EspecieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EspecieServiceImpl implements EspecieService {

    private final EspecieRepository especieRepository;

    @Override
    public EspecieResponse createEspecie(EspecieCreateRequest request) {
        log.info("Creating species: {}", request);

        Especie especie = Especie.builder()
                .nomeEspecie(request.getNomeEspecie())
                .preco(request.getPreco())
                .build();

        Especie savedEspecie = especieRepository.save(especie);
        log.info("Species created successfully: {}", savedEspecie);
        return mapToEspecieResponse(savedEspecie);
    }

    @Override
    public EspecieResponse getEspecieById(Integer id) {
        log.info("Fetching species by id: {}", id);

        Especie especie = especieRepository.findById(id)
                .orElseThrow(() -> new EspecieNotFoundException("Espécie não encontrada!"));
        return mapToEspecieResponse(especie);
    }

    @Override
    public List<EspecieResponse> getAllEspecies() {
        log.info("Fetching all species");

        return especieRepository.findAll().stream()
                .map(this::mapToEspecieResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EspecieResponse updateEspecie(Integer id, EspecieCreateRequest request) {
        log.info("Updating species with id: {}", id);

        Especie especie = especieRepository.findById(id)
                .orElseThrow(() -> new EspecieNotFoundException("Espécie não encontrada!"));

        especie.setNomeEspecie(request.getNomeEspecie());
        especie.setPreco(request.getPreco());

        Especie updatedEspecie = especieRepository.save(especie);
        log.info("Species updated successfully: {}", updatedEspecie);
        return mapToEspecieResponse(updatedEspecie);
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

    private EspecieResponse mapToEspecieResponse(Especie especie) {
        return new EspecieResponse(especie.getId(),
                especie.getNomeEspecie(),
                especie.getPreco());
    }
}
