package com.application.petcare.services;

import com.application.petcare.dto.employee.EmployeeCreateRequest;
import com.application.petcare.dto.employee.EmployeeResponse;
import com.application.petcare.dto.employee.EmployeeUpdateRequest;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    EmployeeResponse createEmployee(EmployeeCreateRequest request);
    EmployeeResponse findById(UUID id);
    EmployeeResponse updateEmployee(UUID id, EmployeeUpdateRequest request);
    void deleteEmployee(UUID id);
    List<EmployeeResponse> findAllEmployees();
}
