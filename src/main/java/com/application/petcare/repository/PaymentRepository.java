package com.application.petcare.repository;

import com.application.petcare.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findByIdInAndDeletedAtIsNull (List<Integer> paymentIds);
}
