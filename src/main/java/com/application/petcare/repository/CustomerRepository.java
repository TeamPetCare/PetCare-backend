package com.application.petcare.repository;

import com.application.petcare.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
