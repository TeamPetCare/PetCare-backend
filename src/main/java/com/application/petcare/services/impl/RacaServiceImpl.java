package com.application.petcare.services.impl;

import com.application.petcare.dto.raca.RacaCreateRequest;
import com.application.petcare.dto.raca.RacaResponse;
import com.application.petcare.entities.Raca;
import com.application.petcare.repository.RacaRepository;
import com.application.petcare.services.RacaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RacaServiceImpl implements RacaService {

    private final RacaRepository racaRepository;

    @Override
    public RacaResponse createRaca(RacaCreateRequest request) {
        Raca raca = Raca.builder()
                .tipoRaca(request.getTipoRaca())
                .preco(request.getPreco())
                .build();

        Raca savedRaca = racaRepository.save(raca);
        return mapToRacaResponse(savedRaca);
    }

    @Override
    public RacaResponse updateRaca(Integer id, RacaCreateRequest request) {
        Raca raca = racaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Raça não encontrada"));

        raca.setTipoRaca(request.getTipoRaca());
        raca.setPreco(request.getPreco());
        Raca updatedRaca = racaRepository.save(raca);
        return mapToRacaResponse(updatedRaca);
    }

    @Override
    public RacaResponse getRacaById(Integer id) {
        Raca raca = racaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Raça não encontrada"));
        return mapToRacaResponse(raca);
    }

    @Override
    public List<RacaResponse> getAllRacas() {
        return racaRepository.findAll().stream()
                .map(this::mapToRacaResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRaca(Integer id) {
        racaRepository.deleteById(id);
    }

    private RacaResponse mapToRacaResponse(Raca raca){
        return new RacaResponse(
                raca.getId(),
                raca.getTipoRaca(),
                raca.getPreco()
        );
    }
}
