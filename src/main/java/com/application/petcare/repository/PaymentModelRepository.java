package com.application.petcare.repository;

import com.application.petcare.entities.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentModelRepository extends JpaRepository<PaymentModel, Integer> {
    List<PaymentModel> findByIdInAndDeletedAtIsNull (List<Integer> paymentIds);
    List<PaymentModel> findAllByDeletedAtIsNull();
    Optional<PaymentModel> findByPaymentId (String paymentId);

}
