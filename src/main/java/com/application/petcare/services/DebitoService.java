package com.application.petcare.services;

import com.application.petcare.dto.pagamento.debito.DebitoCreateRequest;
import com.application.petcare.dto.pagamento.debito.DebitoResponse;

import java.util.List;

public interface DebitoService {
    DebitoResponse createDebito (DebitoCreateRequest request);
    DebitoResponse findById(Integer id);
    void deleteDebito(Integer id);
    List<DebitoResponse> findAllDebito();
}
