package com.application.petcare.controller.impl;

import com.application.petcare.controller.CustomerController;
import com.application.petcare.dto.customer.CustomerCreateRequest;
import com.application.petcare.dto.customer.CustomerResponse;
import com.application.petcare.dto.customer.CustomerUpdateRequest;
import com.application.petcare.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CustomerControllerImpl implements CustomerController {

    private final CustomerService customerService;

    @Override
    public ResponseEntity<CustomerResponse> createCustomer(CustomerCreateRequest customerCreateRequest) {
        CustomerResponse createdCustomer = customerService.createCustomer(customerCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @Override
    public ResponseEntity<CustomerResponse> getCustomerById(UUID id) {
        CustomerResponse customerResponse = customerService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(customerResponse);
    }

    @Override
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        List<CustomerResponse> customers = customerService.getAllCustomers();
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @Override
    public ResponseEntity<CustomerResponse> updateCustomer(UUID id, CustomerUpdateRequest customerUpdateRequest) {
        CustomerResponse updatedCustomer = customerService.updateCustomer(id, customerUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCustomer);
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(UUID id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
