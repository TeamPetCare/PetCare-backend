package com.application.petcare.services.impl;

import com.application.petcare.dto.pagamento.debito.DebitoCreateRequest;
import com.application.petcare.dto.pagamento.debito.DebitoResponse;
import com.application.petcare.entities.Debito;
import com.application.petcare.repository.DebitoRepository;
import com.application.petcare.services.DebitoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DebitoServiceImpl implements DebitoService {

    private final DebitoRepository debitoRepository;

    @Override
    public DebitoResponse createDebito(DebitoCreateRequest request) {
        Debito debito = Debito.builder()
                .valor(request.getValor())
                .dataPagamento(request.getDataPagamento())
                .idPagamento(request.getIdPagamento())
                .numeroCartao(request.getNumeroCartao())
                .nomeTitular(request.getNomeTitular())
                .validade(request.getValidade())
                .codigoDeSeguranca(request.getCodigoDeSeguranca())
                .build();
        Debito savedDebito = debitoRepository.save(debito);
        return mapToDebitoResponse(savedDebito);
    }

    @Override
    public DebitoResponse findById(Integer id) {
        Debito debito = debitoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Débito not found"));
        return mapToDebitoResponse(debito);
    }

    @Override
    public void deleteDebito(Integer id) {
        debitoRepository.deleteById(id);
    }

    @Override
    public List<DebitoResponse> findAllDebito() {
        List<Debito> debitos = debitoRepository.findAll();
        return debitos.stream()
                .map(this::mapToDebitoResponse)
                .toList();
    }

    private DebitoResponse mapToDebitoResponse(Debito debito) {
        return new DebitoResponse(
                debito.getId(),
                debito.getValor(),
                debito.getDataPagamento(),
                debito.getIdPagamento(),
                debito.getNumeroCartao(),
                debito.getNomeTitular(),
                debito.getValidade(),
                debito.getCodigoDeSeguranca()
        );
    }
}

