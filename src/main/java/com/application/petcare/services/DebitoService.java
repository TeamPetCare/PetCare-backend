package com.application.petcare.services;


import com.application.petcare.dto.pagamento.debito.DebitoCreateRequest;
import com.application.petcare.dto.pagamento.debito.DebitoResponse;
import com.application.petcare.entities.Debito;

import java.util.List;

public interface DebitoService {
    DebitoResponse createCredito (DebitoCreateRequest request);
    DebitoResponse findById(Integer id);
    void deleteCredito(Integer id);
    List<Debito> findAllCredito();
}
