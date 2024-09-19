package com.application.petcare.services.impl;

import com.application.petcare.dto.servicos.ServicosCreateRequest;
import com.application.petcare.dto.servicos.ServicosResponse;
import com.application.petcare.dto.servicos.ServicosUpdateRequest;
import com.application.petcare.entities.Servicos;
import com.application.petcare.repository.ServicoRepository;
import com.application.petcare.services.ServicosService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServicosServiceImpl implements ServicosService {

    private final ServicoRepository servicoRepository;

    @Override
    public ServicosResponse createServico(ServicosCreateRequest request) {
        Servicos servico = Servicos.builder()
                .nome(request.getNome())
                .descricao(request.getDescricao())
                .preco(request.getPreco())
                .build();

        Servicos savedServico = servicoRepository.save(servico);
        return mapToServicosResponse(savedServico);
    }

    @Override
    public ServicosResponse updateServico(Integer id, ServicosUpdateRequest request) {
        Servicos servico = servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servico não encontrado"));

        servico.setNome(request.getNome());
        servico.setDescricao(request.getDescricao());
        servico.setPreco(request.getPreco());

        Servicos savedServico = servicoRepository.save(servico);
        return mapToServicosResponse(savedServico);
    }

    @Override
    public void deleteServico(Integer id) {
        servicoRepository.deleteById(id);
    }

    @Override
    public List<ServicosResponse> findAllServicos() {
        return servicoRepository.findAll().stream()
                .map(this::mapToServicosResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ServicosResponse getServicoById(Integer id) {
        Servicos servico = servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servico não encontrado"));
        return mapToServicosResponse(servico);
    }

    private ServicosResponse mapToServicosResponse(Servicos servicos){
        return new ServicosResponse(
                servicos.getId(),
                servicos.getNome(),
                servicos.getDescricao(),
                servicos.getPreco()
        );
    }

}
