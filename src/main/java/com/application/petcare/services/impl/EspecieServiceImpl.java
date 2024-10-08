package com.application.petcare.services.impl;

import com.application.petcare.dto.especie.EspecieCreateRequest;
import com.application.petcare.dto.especie.EspecieResponse;
import com.application.petcare.entities.Especie;
import com.application.petcare.repository.EspecieRepository;
import com.application.petcare.services.EspecieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EspecieServiceImpl implements EspecieService {

    private final EspecieRepository especieRepository;

    @Override
    public EspecieResponse createEspecie(EspecieCreateRequest request) {
        Especie especie = Especie.builder()
                .nomeEspecie(request.getNomeEspecie())
                .preco(request.getPreco())
                .build();

        Especie savedEspecie = especieRepository.save(especie);
        return mapToEspecieResponse(savedEspecie);
    }

    @Override
    public EspecieResponse getEspecieById(Integer id) {
        Especie especie = especieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Espécie não encontrada!"));
        return mapToEspecieResponse(especie);
    }

    @Override
    public List<EspecieResponse> getAllEspecies() {
        return especieRepository.findAll().stream()
                .map(this::mapToEspecieResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EspecieResponse updateEspecie(Integer id, EspecieCreateRequest request) {
        Especie especie = especieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Espécie não encontrada!"));

        especie.setNomeEspecie(request.getNomeEspecie());
        especie.setPreco(request.getPreco());

        Especie updatedEspecie = especieRepository.save(especie);
        return mapToEspecieResponse(updatedEspecie);
    }

    @Override
    public void deleteEspecie(Integer id) {
        especieRepository.deleteById(id);
    }

    private EspecieResponse mapToEspecieResponse(Especie especie) {
        return new EspecieResponse(especie.getId(),
                especie.getNomeEspecie(),
                especie.getPreco());
    }
}