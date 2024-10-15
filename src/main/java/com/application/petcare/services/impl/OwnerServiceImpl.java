package com.application.petcare.services.impl;

import com.application.petcare.dto.owner.OwnerCreateRequest;
import com.application.petcare.dto.owner.OwnerResponse;
import com.application.petcare.dto.owner.OwnerUpdateRequest;
import com.application.petcare.entities.Employee;
import com.application.petcare.entities.Owner;
import com.application.petcare.repository.EmployeeRepository;
import com.application.petcare.repository.OwnerRepository;
import com.application.petcare.services.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public OwnerResponse createOwner(OwnerCreateRequest request) {
        List<Employee> employees = employeeRepository.findAllById(request.getEmployeeIds());
        Owner owner = Owner.builder()
                .name(request.getName())
                .employees(employees)
                .cnpj(request.getCnpj())
                .build();
        Owner savedOwner = ownerRepository.save(owner);
        return mapToOwnerResponse(savedOwner);
    }

    @Override
    public OwnerResponse findById(Integer id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Owner not found with id: " + id));
        return mapToOwnerResponse(owner);
    }

    @Override
    public OwnerResponse updateOwner(Integer id, OwnerUpdateRequest request) {
        Owner existingOwner = ownerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Owner not found with id: " + id));

        List<Employee> employees = employeeRepository.findAllById(request.getEmployeeIds());

        existingOwner.setName(request.getName());
        existingOwner.setEmployees(employees);
        existingOwner.setCnpj(request.getCnpj());

        Owner updatedOwner = ownerRepository.save(existingOwner);
        return mapToOwnerResponse(updatedOwner);
    }

    @Override
    public void deleteOwner(Integer id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Owner not found with id: " + id));
        ownerRepository.delete(owner);
    }

    @Override
    public List<OwnerResponse> findAllOwners() {
        List<Owner> owners = ownerRepository.findAll();
        return owners.stream()
                .map(this::mapToOwnerResponse)
                .collect(Collectors.toList());
    }



    private OwnerResponse mapToOwnerResponse(Owner owner) {
        return new OwnerResponse(
                owner.getId(),
                owner.getName(),
                owner.getCnpj(),
                owner.getEmployees().stream().map(Employee::getId).collect(Collectors.toList())
        );
    }
}

