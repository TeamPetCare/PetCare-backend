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
    public ServicesResponse createService(ServicesCreateRequest request) {
        log.info("Creating service: {}", request);

        Services servico = Services.builder()
                .name(request.getName())
                .note(request.getNote())
                .priece(request.getPriece())
                .estimatedTime(request.getEstimatedTime())
                .disponibility(request.getDisponibility())
                .build();

        Services savedServico = servicesRepository.save(servico);
        log.info("Service created successfully: {}", savedServico);
        return mapToServicosResponse(savedServico);
    }

    @Override
    public ServicesResponse updateService(Integer id, ServicesUpdateRequest request) {
        log.info("Updating service with id: {}", id);

        Services servico = servicesRepository.findById(id)
                .orElseThrow(() -> new ServicoNotFoundException("Servico não encontrado"));

        servico.setName(request.getName());
        servico.setNote(request.getNote());
        servico.setPriece(request.getPriece());
        servico.setEstimatedTime(request.getEstimatedTime());
        servico.setDisponibility(request.getDisponibility());

        Services updatedServico = servicesRepository.save(servico);
        log.info("Service updated successfully: {}", updatedServico);
        return mapToServicosResponse(updatedServico);
    }

    @Override
    public void deleteService(Integer id) {
        log.info("Deleting service with id: {}", id);

        if (!servicesRepository.existsById(id)) {
            throw new ServicoNotFoundException("Servico não encontrado");
        }
        servicesRepository.deleteById(id);
        log.info("Service deleted successfully with id: {}", id);
    }

    @Override
    public List<ServicesResponse> findAllServices() {
        log.info("Fetching all services");

        return servicesRepository.findAll().stream()
                .map(this::mapToServicosResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ServicesResponse getServiceById(Integer id) {
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
                services.getPriece(),
                services.getEstimatedTime(),
                services.getDisponibility()
        );
    }
}
