package com.application.petcare.services.impl;

import com.application.petcare.dto.size.SizeCreateRequest;
import com.application.petcare.dto.size.SizeResponse;
import com.application.petcare.entities.Size;
import com.application.petcare.exceptions.SizeNotFoundException;
import com.application.petcare.repository.SizeRepository;
import com.application.petcare.services.SizeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SizeServiceImpl implements SizeService {

    private final SizeRepository sizeRepository;

    @Override
    public SizeResponse createSize(SizeCreateRequest request) {
        log.info("Creating size: {}", request);

        Size size = Size.builder()
                .sizeType(request.getSizeType())
                .priece(request.getPriece())
                .build();

        Size savedSize = sizeRepository.save(size);
        log.info("Size created successfully: {}", savedSize);
        return mapToSizeResponse(savedSize);
    }

    @Override
    public SizeResponse updateSize(Integer id, SizeCreateRequest request) {
        log.info("Updating size with id: {}", id);

        Size size = sizeRepository.findById(id)
                .orElseThrow(() -> new SizeNotFoundException("Tamanho não encontrado"));

        size.setSizeType(request.getSizeType());
        size.setPriece(request.getPriece());
        Size updatedSize = sizeRepository.save(size);
        log.info("Size updated successfully: {}", updatedSize);
        return mapToSizeResponse(updatedSize);
    }

    @Override
    public void deleteSize(Integer id) {
        log.info("Deleting size with id: {}", id);

        if (!sizeRepository.existsById(id)) {
            throw new SizeNotFoundException("Tamanho não encontrado");
        }
        sizeRepository.deleteById(id);
        log.info("Size deleted successfully with id: {}", id);
    }

    @Override
    public SizeResponse getSizeById(Integer id) {
        log.info("Fetching size by id: {}", id);

        Size size = sizeRepository.findById(id)
                .orElseThrow(() -> new SizeNotFoundException("Tamanho não encontrado"));
        return mapToSizeResponse(size);
    }

    @Override
    public List<SizeResponse> getAllSizes() {
        log.info("Fetching all sizes");

        return sizeRepository.findAll().stream()
                .map(this::mapToSizeResponse)
                .collect(Collectors.toList());
    }

    private SizeResponse mapToSizeResponse(Size size) {
        return new SizeResponse(size.getId(), size.getSizeType(), size.getPriece());
    }
}
