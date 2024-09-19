package com.application.petcare.controller;

import com.application.petcare.dto.employee.EmployeeCreateRequest;
import com.application.petcare.dto.employee.EmployeeResponse;
import com.application.petcare.dto.employee.EmployeeUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Employee", description = "Gerenciar funcionários")
@RequestMapping("/api/employees")
public interface EmployeeController {

    @Operation(summary = "Criar um novo funcionário")
    @PostMapping
    ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeCreateRequest employeeCreateRequest);

    @Operation(summary = "Buscar funcionário por ID")
    @GetMapping("/{id}")
    ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable UUID id);

    @Operation(summary = "Buscar todos os funcionários")
    @GetMapping
    ResponseEntity<List<EmployeeResponse>> getAllEmployees();

    @Operation(summary = "Atualizar funcionário por ID")
    @PutMapping("/{id}")
    ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable UUID id,
            @RequestBody EmployeeUpdateRequest employeeUpdateRequest);

    @Operation(summary = "Excluir funcionário por ID")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteEmployee(@PathVariable UUID id);
}
