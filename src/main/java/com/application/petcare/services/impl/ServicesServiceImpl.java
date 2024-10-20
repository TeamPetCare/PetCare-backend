package com.application.petcare.services.impl;

import com.application.petcare.dto.services.ServicesCreateRequest;
import com.application.petcare.dto.services.ServicesResponse;
import com.application.petcare.dto.services.ServicesUpdateRequest;
import com.application.petcare.entities.Services;
import com.application.petcare.exceptions.ServicoNotFoundException;
import com.application.petcare.repository.ServicesRepository;
import com.application.petcare.services.ServicesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServicesServiceImpl implements ServicesService {

    private final ServicesRepository servicesRepository;

    @Override
    public ServicesResponse createServico(ServicesCreateRequest request) {
        log.info("Creating service: {}", request);

        Services servico = Services.builder()
                .name(request.getName())
                .note(request.getNote())
                .price(request.getPrice())
                .estimatedTime(request.getEstimatedTime())
                .disponibility(request.getDisponibility())
                .build();

        Services savedServico = servicesRepository.save(servico);
        log.info("Service created successfully: {}", savedServico);
        return mapToServicosResponse(savedServico);
    }

    @Override
    public ServicesResponse updateServico(Integer id, ServicesUpdateRequest request) {
        log.info("Updating service with id: {}", id);

        Services servico = servicesRepository.findById(id)
                .orElseThrow(() -> new ServicoNotFoundException("Servico não encontrado"));

        servico.setName(request.getName());
        servico.setNote(request.getNote());
        servico.setPrice(request.getPrice());
        servico.setEstimatedTime(request.getEstimatedTime());
        servico.setDisponibility(request.getDisponibility());

        Services updatedServico = servicesRepository.save(servico);
        log.info("Service updated successfully: {}", updatedServico);
        return mapToServicosResponse(updatedServico);
    }

    @Override
    public void deleteServico(Integer id) {
        log.info("Deleting service with id: {}", id);

        if (!servicesRepository.existsById(id)) {
            throw new ServicoNotFoundException("Servico não encontrado");
        }
        servicesRepository.deleteById(id);
        log.info("Service deleted successfully with id: {}", id);
    }

    @Override
    public List<ServicesResponse> findAllServicos() {
        log.info("Fetching all services");

        return servicesRepository.findAll().stream()
                .map(this::mapToServicosResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ServicesResponse getServicoById(Integer id) {
        log.info("Fetching service by id: {}", id);

        Services servico = servicesRepository.findById(id)
                .orElseThrow(() -> new ServicoNotFoundException("Servico não encontrado"));
        return mapToServicosResponse(servico);
    }

    private ServicesResponse mapToServicosResponse(Services services) {
        return new ServicesResponse(
                services.getId(),
                services.getName(),
                services.getNote(),
                services.getPrice(),
                services.getEstimatedTime(),
                services.getDisponibility()
        );
    }
}
