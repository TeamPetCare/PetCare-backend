package com.application.petcare.services;

import com.application.petcare.dto.servicos.ServicosCreateRequest;
import com.application.petcare.dto.servicos.ServicosResponse;
import com.application.petcare.dto.servicos.ServicosUpdateRequest;

import java.util.List;

public interface ServicosService {
    ServicosResponse createServico(ServicosCreateRequest request);
    ServicosResponse updateServico(Integer id, ServicosCreateRequest request);
    void deleteServico(Integer id);
    List<ServicosResponse> findAllServicos();

}
