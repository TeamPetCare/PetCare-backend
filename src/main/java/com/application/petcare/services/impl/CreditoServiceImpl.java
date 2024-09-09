package com.application.petcare.services.impl;

import com.application.petcare.dto.pagamento.credito.CreditoCreateRequest;
import com.application.petcare.dto.pagamento.credito.CreditoResponse;
import com.application.petcare.entities.Credito;
import com.application.petcare.repository.CreditoRepository;
import com.application.petcare.services.CreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditoServiceImpl implements CreditoService {

    private final CreditoRepository creditoRepository;

    @Override
    public CreditoResponse createCredito(CreditoCreateRequest request) {
        Credito credito = Credito.builder()
                .valor(request.getValor())
                .dataPagamento(request.getDataPagamento())
                .idPagamento(request.getIdPagamento())
                .numeroCartao(request.getNumeroCartao())
                .nomeTitular(request.getNomeTitular())
                .validade(request.getValidade())
                .codigoDeSeguranca(request.getCodigoDeSeguranca())
                .build();
        Credito savedCredito = creditoRepository.save(credito);
        return mapToCreditoResponse(savedCredito);
    }

    @Override
    public CreditoResponse findById(Integer id) {
        Credito credito = creditoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crédito not found"));
        return mapToCreditoResponse(credito);
    }

    @Override
    public void deleteCredito(Integer id) {
        creditoRepository.deleteById(id);
    }

    @Override
    public List<CreditoResponse> findAllCredito() {
        List<Credito> creditos = creditoRepository.findAll();
        return creditos.stream()
                .map(this::mapToCreditoResponse)
                .toList();
    }

    private CreditoResponse mapToCreditoResponse(Credito credito) {
        return new CreditoResponse(
                credito.getId(),
                credito.getValor(),
                credito.getDataPagamento(),
                credito.getIdPagamento(),
                credito.getNumeroCartao(),
                credito.getNomeTitular(),
                credito.getValidade(),
                credito.getCodigoDeSeguranca()
        );
    }
}
