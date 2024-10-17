package com.application.petcare.services.impl;

import com.application.petcare.dto.servicos.ServicosCreateRequest;
import com.application.petcare.dto.servicos.ServicosResponse;
import com.application.petcare.dto.servicos.ServicosUpdateRequest;
import com.application.petcare.entities.Servicos;
import com.application.petcare.exceptions.ServicoNotFoundException;
import com.application.petcare.repository.ServicoRepository;
import com.application.petcare.services.ServicosService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServicosServiceImpl implements ServicosService {

    private final ServicoRepository servicoRepository;

    @Override
    public ServicosResponse createServico(ServicosCreateRequest request) {
        log.info("Creating service: {}", request);

        Servicos servico = Servicos.builder()
                .nome(request.getNome())
                .descricao(request.getDescricao())
                .preco(request.getPreco())
                .build();

        Servicos savedServico = servicoRepository.save(servico);
        log.info("Service created successfully: {}", savedServico);
        return mapToServicosResponse(savedServico);
    }

    @Override
    public ServicosResponse updateServico(Integer id, ServicosUpdateRequest request) {
        log.info("Updating service with id: {}", id);

        Servicos servico = servicoRepository.findById(id)
                .orElseThrow(() -> new ServicoNotFoundException("Servico não encontrado"));

        servico.setNome(request.getNome());
        servico.setDescricao(request.getDescricao());
        servico.setPreco(request.getPreco());

        Servicos updatedServico = servicoRepository.save(servico);
        log.info("Service updated successfully: {}", updatedServico);
        return mapToServicosResponse(updatedServico);
    }

    @Override
    public void deleteServico(Integer id) {
        log.info("Deleting service with id: {}", id);

        if (!servicoRepository.existsById(id)) {
            throw new ServicoNotFoundException("Servico não encontrado");
        }
        servicoRepository.deleteById(id);
        log.info("Service deleted successfully with id: {}", id);
    }

    @Override
    public List<ServicosResponse> findAllServicos() {
        log.info("Fetching all services");

        return servicoRepository.findAll().stream()
                .map(this::mapToServicosResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ServicosResponse getServicoById(Integer id) {
        log.info("Fetching service by id: {}", id);

        Servicos servico = servicoRepository.findById(id)
                .orElseThrow(() -> new ServicoNotFoundException("Servico não encontrado"));
        return mapToServicosResponse(servico);
    }

    private ServicosResponse mapToServicosResponse(Servicos servicos) {
        return new ServicosResponse(
                servicos.getId(),
                servicos.getNome(),
                servicos.getDescricao(),
                servicos.getPreco()
        );
    }
}
