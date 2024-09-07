package com.application.petcare.services.impl;

import com.application.petcare.dto.pagamento.pix.PIXCreateRequest;
import com.application.petcare.dto.pagamento.pix.PIXResponse;
import com.application.petcare.entities.PIX;
import com.application.petcare.repository.PIXRepository;
import com.application.petcare.services.PIXService;

import java.util.List;

public class PIXServiceImpl implements PIXService {

    private PIXRepository pixRepository;

    @Override
    public PIXResponse createPix(PIXCreateRequest request) {
        return null;
    }

    @Override
    public PIXResponse findById(Integer id) {
        return null;
    }

    @Override
    public void deleteEmployee(Integer id) {

    }

    @Override
    public List<PIX> findAllPIX() {
        return List.of();
    }
}
