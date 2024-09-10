package com.application.petcare.services;

import com.application.petcare.dto.pagamento.credito.CreditoCreateRequest;
import com.application.petcare.dto.pagamento.credito.CreditoResponse;

import java.util.List;

public interface CreditoService {

    CreditoResponse createCredito (CreditoCreateRequest request);
    CreditoResponse findById(Integer id);
    void deleteCredito(Integer id);
    List<CreditoResponse> findAllCredito();
}
