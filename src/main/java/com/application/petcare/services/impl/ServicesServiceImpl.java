package com.application.petcare.services.impl;

import com.application.petcare.dto.services.ServicesCreateRequest;
import com.application.petcare.dto.services.ServicesResponse;
import com.application.petcare.dto.services.ServicesUpdateRequest;
import com.application.petcare.entities.Schedule;
import com.application.petcare.entities.Services;
import com.application.petcare.exceptions.ResourceNotFoundException;
import com.application.petcare.exceptions.ServicoNotFoundException;
import com.application.petcare.repository.ScheduleRepository;
import com.application.petcare.repository.ServicesRepository;
import com.application.petcare.services.ServicesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServicesServiceImpl implements ServicesService {

    private final ServicesRepository servicesRepository;

    private final ScheduleRepository scheduleRepository;

    @Override
    public ServicesResponse createService(ServicesCreateRequest request) {
        log.info("Creating service: {}", request);

        Services servico = Services.builder()
                .name(request.getName())
                .note(request.getNote())
                .price(request.getPrice())
                .estimatedTime(request.getEstimatedTime())
                .disponibility(request.getDisponibility())
                .deletedAt(null)
                .isExclusive(request.getIsExclusive())
//                .schedules(scheduleRepository.findAllByIdIn(request.getScheduleIds())
//                        .filter(schedules -> !schedules.isEmpty())
//                        .orElseThrow(() -> new ResourceNotFoundException("Schedule not foun")))
                .build();

        Services savedServico = servicesRepository.save(servico);
        log.info("Service created successfully: {}", savedServico);
        return mapToServicosResponse(savedServico);
    }

    @Override
    public ServicesResponse updateService(Integer id, ServicesUpdateRequest request) {
        log.info("Updating service with id: {}", id);

        Services servico = servicesRepository.findById(id)
                .orElseThrow(() -> new ServicoNotFoundException("Servico não encontrado"));

        servico.setName(request.getName());
        servico.setNote(request.getNote());
        servico.setPrice(request.getPrice());
        servico.setEstimatedTime(request.getEstimatedTime());
        servico.setDisponibility(request.getDisponibility());
        servico.setIsExclusive(request.getIsExclusive());
//        servico.setSchedules(scheduleRepository.findAllByIdIn(request.getScheduleIds())
//                .filter(schedules -> !schedules.isEmpty())
//                .orElseThrow(() -> new ResourceNotFoundException("Schedule not foun")));

        Services updatedServico = servicesRepository.save(servico);
        log.info("Service updated successfully: {}", updatedServico);
        return mapToServicosResponse(updatedServico);
    }

    @Override
    public void deleteService(Integer id) {
        log.info("Deleting service with id: {}", id);

        Services service = servicesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found"));

//        for (Schedule schedule : service.getSchedules()) {
//            schedule.getServices().remove(service);
//            scheduleRepository.save(schedule);
//        }
        service.setDeletedAt(LocalDateTime.now());
        servicesRepository.save(service);
        log.info("Service deleted successfully with id: {}", id);
    }

    @Override
    public List<ServicesResponse> findAllServices() {
        log.info("Fetching all services");

        return servicesRepository.findAllByDeletedAtIsNull().stream()
                .map(this::mapToServicosResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ServicesResponse getServiceById(Integer id) {
        log.info("Fetching service by id: {}", id);

        Services servico = servicesRepository.findById(id)
                .orElseThrow(() -> new ServicoNotFoundException("Servico não encontrado"));
        return mapToServicosResponse(servico);
    }

    @Override
    public List<ServicesResponse> getServicesByIdsList(List<Integer> ids) {
        return servicesRepository.findAllByIdInAndDeletedAtIsNull(ids).stream().map(this::mapToServicosResponse).collect(Collectors.toList());
    }

    private ServicesResponse mapToServicosResponse(Services services) {

//        Integer[] scheduleIds = new Integer[services.getSchedules().size()];
//        for (int i = 0; i < scheduleIds.length; i++) {
//            scheduleIds[i] = services.getSchedules().get(i).getId();
//        }

        return new ServicesResponse(
                services.getId(),
                services.getName(),
                services.getNote(),
                services.getPrice(),
                services.getEstimatedTime(),
                services.getDisponibility(),
                services.getIsExclusive()
        );
    }
}
