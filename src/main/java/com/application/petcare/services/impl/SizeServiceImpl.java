package com.application.petcare.services.impl;

import com.application.petcare.dto.size.SizeCreateRequest;
import com.application.petcare.dto.size.SizeResponse;
import com.application.petcare.entities.Size;
import com.application.petcare.repository.SizeRepository;
import com.application.petcare.services.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {

    private final SizeRepository sizeRepository;

    @Override
    public SizeResponse createSize(SizeCreateRequest request) {
        Size size = Size.builder()
                .tipoTamanho(request.getTipoTamanho())
                .preco(request.getPreco())
                .build();

        Size savedSize = sizeRepository.save(size);
        return mapToSizeResponse(savedSize);
    }

    @Override
    public SizeResponse updateSize(UUID id, SizeCreateRequest request) {
        Size size = sizeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tamanho não encontrado"));

        size.setTipoTamanho(request.getTipoTamanho());
        size.setPreco(request.getPreco());
        Size updatedSize = sizeRepository.save(size);

        return mapToSizeResponse(updatedSize);
    }

    @Override
    public void deleteSize(UUID id) {
        sizeRepository.deleteById(id);
    }

    @Override
    public SizeResponse getSizeById(UUID id) {
        Size size = sizeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tamanho não encontrado"));
        return mapToSizeResponse(size);
    }

    @Override
    public List<SizeResponse> getAllSizes() {
        return sizeRepository.findAll().stream()
                .map(this::mapToSizeResponse)
                .collect(Collectors.toList());
    }

    private SizeResponse mapToSizeResponse(Size size) {
        return new SizeResponse(size.getId(), size.getTipoTamanho(), size.getPreco());
    }
}