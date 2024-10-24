package com.application.petcare.services;


import com.application.petcare.dto.payment.PaymentCreateRequest;
import com.application.petcare.dto.payment.PaymentResponse;

import java.util.List;

public interface PaymentService {
    PaymentResponse createPayment(PaymentCreateRequest request);

    PaymentResponse updatePayment(Integer id, PaymentCreateRequest request);

    PaymentResponse getPaymentById(Integer id);

    List<PaymentResponse> getAllPayments();

    void deletePayment(Integer id);
}
