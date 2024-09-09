package com.application.petcare.controller;

import com.application.petcare.dto.employee.EmployeeCreateRequest;
import com.application.petcare.dto.employee.EmployeeResponse;
import com.application.petcare.dto.employee.EmployeeUpdateRequest;
import com.application.petcare.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Tag(name = "Employee", description = "Endpoints para gerenciar funcionários")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Operation(summary = "Criar um novo funcionário")
    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeCreateRequest employeeCreateRequest) {
        EmployeeResponse createdEmployee = employeeService.createEmployee(employeeCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    @Operation(summary = "Buscar funcionário por ID")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable UUID id) {
        EmployeeResponse employeeResponse = employeeService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
    }

    @Operation(summary = "Buscar todos os funcionários")
    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        List<EmployeeResponse> employees = employeeService.findAllEmployees();
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }

    @Operation(summary = "Atualizar funcionário por ID")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable UUID id,
            @RequestBody EmployeeUpdateRequest employeeUpdateRequest) {
        EmployeeResponse updatedEmployee = employeeService.updateEmployee(id, employeeUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(updatedEmployee);
    }

    @Operation(summary = "Excluir funcionário por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable UUID id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
