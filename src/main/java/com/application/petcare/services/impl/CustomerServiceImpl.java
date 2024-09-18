package com.application.petcare.services.impl;

import com.application.petcare.dto.customer.CustomerCreateRequest;
import com.application.petcare.dto.customer.CustomerResponse;
import com.application.petcare.dto.customer.CustomerUpdateRequest;

import com.application.petcare.entities.Customer;
import com.application.petcare.repository.CustomerRepository;
import com.application.petcare.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse createCustomer(CustomerCreateRequest request) {
        Customer customer = Customer.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .type(request.getRole())
                .cpf(request.getCpf())
                .build();
        Customer savedCustomer = customerRepository.save(customer);
        return mapToCustomerResponse(savedCustomer);
    }

    @Override
    public CustomerResponse findById(UUID id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return mapToCustomerResponse(customer);
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(this::mapToCustomerResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponse updateCustomer(UUID id, CustomerUpdateRequest request) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        existingCustomer.setName(request.getName());
        existingCustomer.setEmail(request.getEmail());
        existingCustomer.setPassword(request.getPassword());
        existingCustomer.setCpf(request.getCpf()); // Atualizando o CPF
        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return mapToCustomerResponse(updatedCustomer);
    }

    @Override
    public void deleteCustomer(UUID id) {
        customerRepository.deleteById(id);
    }

    private CustomerResponse mapToCustomerResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getType(),
                customer.getCpf() // Incluindo o CPF na resposta
        );
    }
}
