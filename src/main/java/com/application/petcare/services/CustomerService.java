package com.application.petcare.services;


import com.application.petcare.dto.customer.CustomerCreateRequest;
import com.application.petcare.dto.customer.CustomerResponse;
import com.application.petcare.dto.customer.CustomerUpdateRequest;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerCreateRequest request);
    CustomerResponse findById(Integer id);
    List<CustomerResponse> getAllCustomers();
    CustomerResponse updateCustomer(Integer id, CustomerUpdateRequest request);
    void deleteCustomer(Integer id);
}