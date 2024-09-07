package com.application.petcare.services;

import com.application.petcare.dto.pagamento.credito.CreditoCreateRequest;
import com.application.petcare.dto.pagamento.credito.CreditoResponse;
import com.application.petcare.dto.pagamento.pix.PIXCreateRequest;
import com.application.petcare.dto.pagamento.pix.PIXResponse;
import com.application.petcare.entities.Credito;

import java.util.List;

public interface CreditoService {

    CreditoResponse createCredito (CreditoCreateRequest request);
    CreditoResponse findById(Integer id);
    void deleteCredito(Integer id);
    List<Credito> findAllCredito();
}
