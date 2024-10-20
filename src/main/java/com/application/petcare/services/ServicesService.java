package com.application.petcare.services;

import com.application.petcare.dto.services.ServicesCreateRequest;
import com.application.petcare.dto.services.ServicesResponse;
import com.application.petcare.dto.services.ServicesUpdateRequest;

import java.util.List;

public interface ServicesService {
    ServicesResponse createServico(ServicesCreateRequest request);
    ServicesResponse updateServico(Integer id, ServicesUpdateRequest request);
    void deleteServico(Integer id);
    List<ServicesResponse> findAllServicos();
    ServicesResponse getServicoById(Integer id);
}
