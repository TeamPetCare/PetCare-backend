package com.application.petcare.services;


import com.application.petcare.dto.customer.CustomerCreateRequest;
import com.application.petcare.dto.customer.CustomerResponse;
import com.application.petcare.dto.customer.CustomerUpdateRequest;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerCreateRequest request);
    CustomerResponse findById(UUID id);
    List<CustomerResponse> getAllCustomers();
    CustomerResponse updateCustomer(UUID id, CustomerUpdateRequest request);
    void deleteCustomer(UUID id);
}