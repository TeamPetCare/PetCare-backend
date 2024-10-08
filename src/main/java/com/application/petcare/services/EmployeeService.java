package com.application.petcare.services;

import com.application.petcare.dto.employee.EmployeeCreateRequest;
import com.application.petcare.dto.employee.EmployeeResponse;
import com.application.petcare.dto.employee.EmployeeUpdateRequest;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse createEmployee(EmployeeCreateRequest request);
    EmployeeResponse findById(Integer id);
    EmployeeResponse updateEmployee(Integer id, EmployeeUpdateRequest request);
    void deleteEmployee(Integer id);
    List<EmployeeResponse> findAllEmployees();
}
