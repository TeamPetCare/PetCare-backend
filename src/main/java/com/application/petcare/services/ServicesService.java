package com.application.petcare.services;

import com.application.petcare.dto.services.ServicesCreateRequest;
import com.application.petcare.dto.services.ServicesResponse;
import com.application.petcare.dto.services.ServicesUpdateRequest;

import java.util.List;

public interface ServicesService {
    ServicesResponse createService(ServicesCreateRequest request);
    ServicesResponse updateService(Integer id, ServicesUpdateRequest request);
    void deleteService(Integer id);
    List<ServicesResponse> findAllServices();
    ServicesResponse getServiceById(Integer id);
    List<ServicesResponse> getServicesByIdsList(List<Integer> ids);
}