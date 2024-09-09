package com.application.petcare.services.impl;

import com.application.petcare.dto.customer.CustomerResponse;
import com.application.petcare.dto.pagamento.pix.PIXCreateRequest;
import com.application.petcare.dto.pagamento.pix.PIXResponse;
import com.application.petcare.entities.Customer;
import com.application.petcare.entities.Employee;
import com.application.petcare.entities.PIX;
import com.application.petcare.repository.PIXRepository;
import com.application.petcare.services.PIXService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PIXServiceImpl implements PIXService {

    private final PIXRepository pixRepository;

    @Override
    public PIXResponse createPIX(PIXCreateRequest request) {
        PIX pix = PIX.builder()
                .valor(request.getValor())
                .dataPagamento(request.getDataPagamento())
                .idPagamento(request.getIdPagamento())
                .chavePix(request.getChavePix())
                .build();
        PIX savedPIX = pixRepository.save(pix);
        return mapToPIXResponse(savedPIX);
    }

    @Override
    public PIXResponse findById(Integer id) {
        PIX pix = pixRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PIX not found"));
        return mapToPIXResponse(pix);
    }

    @Override
    public void deletePIX(Integer id) {
        pixRepository.deleteById(id);
    }

    @Override
    public List<PIXResponse> findAllPIX() {
        List<PIX> pix = pixRepository.findAll();
        return pix.stream()
                .map(this::mapToPIXResponse)
                .toList();
    }

    private PIXResponse mapToPIXResponse(PIX pix) {
        return new PIXResponse(
                pix.getId(),
                pix.getValor(),
                pix.getDataPagamento(),
                pix.getIdPagamento(),
                pix.getChavePix()
        );
    }
}
