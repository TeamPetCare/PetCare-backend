package com.application.petcare.services;

import com.application.petcare.dto.pagamento.pix.PIXCreateRequest;
import com.application.petcare.dto.pagamento.pix.PIXResponse;
import com.application.petcare.entities.PIX;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface PIXService {

    PIXResponse createPix (PIXCreateRequest request);
    PIXResponse findById(Integer id);
    void deleteEmployee(Integer id);
    List<PIX> findAllPIX();
}
