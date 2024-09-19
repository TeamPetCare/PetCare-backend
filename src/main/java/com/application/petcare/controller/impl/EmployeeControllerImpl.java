package com.application.petcare.controller.impl;

import com.application.petcare.controller.EmployeeController;
import com.application.petcare.dto.employee.EmployeeCreateRequest;
import com.application.petcare.dto.employee.EmployeeResponse;
import com.application.petcare.dto.employee.EmployeeUpdateRequest;
import com.application.petcare.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class EmployeeControllerImpl implements EmployeeController {

    private final EmployeeService employeeService;

    @Override
    public ResponseEntity<EmployeeResponse> createEmployee(EmployeeCreateRequest employeeCreateRequest) {
        EmployeeResponse createdEmployee = employeeService.createEmployee(employeeCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    @Override
    public ResponseEntity<EmployeeResponse> getEmployeeById(UUID id) {
        EmployeeResponse employeeResponse = employeeService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
    }

    @Override
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        List<EmployeeResponse> employees = employeeService.findAllEmployees();
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }

    @Override
    public ResponseEntity<EmployeeResponse> updateEmployee(UUID id, EmployeeUpdateRequest employeeUpdateRequest) {
        EmployeeResponse updatedEmployee = employeeService.updateEmployee(id, employeeUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(updatedEmployee);
    }

    @Override
    public ResponseEntity<Void> deleteEmployee(UUID id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
