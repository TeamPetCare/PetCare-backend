package com.application.petcare.services.impl;

import com.application.petcare.dto.payment.PaymentCreateRequest;
import com.application.petcare.dto.payment.PaymentResponse;
import com.application.petcare.entities.Payment;
import com.application.petcare.exceptions.ResourceNotFoundException;
import com.application.petcare.repository.PaymentRepository;
import com.application.petcare.repository.UserRepository;
import com.application.petcare.services.PaymentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private UserRepository userRepository;
    private PaymentRepository paymentRepository;

    @Override
    public PaymentResponse createPayment(PaymentCreateRequest request) {
        Payment payment = Payment.builder()
                .priece(request.getPriece())
                .paymentDate(request.getPaymentDate())
                .user(userRepository.findById(request.getUserId())
                        .orElseThrow(() -> new ResourceNotFoundException("User not found")))
                .build();
        Payment savedPayment = paymentRepository.save(payment);
        return mapToPaymentResponse(savedPayment);
    }

    @Override
    public PaymentResponse updatePayment(Integer id, PaymentCreateRequest request) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
        payment.setId(id);
        payment.setPriece(request.getPriece());
        payment.setPaymentDate(request.getPaymentDate());
        payment.setUser(userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found")));

        Payment updatedPayment = paymentRepository.save(payment);
        return mapToPaymentResponse(updatedPayment);
    }

    @Override
    public PaymentResponse getPaymentById(Integer id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
        return mapToPaymentResponse(payment);
    }

    @Override
    public List<PaymentResponse> getAllPayments() {
        return paymentRepository.findAll().stream().map(this::mapToPaymentResponse).toList();
    }

    @Override
    public void deletePayment(Integer id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
        paymentRepository.deleteById(id);
    }

    public PaymentResponse mapToPaymentResponse(Payment payment){
        return PaymentResponse.builder()
                .id(payment.getId())
                .priece(payment.getPriece())
                .paymentDate(payment.getPaymentDate())
                .userId(payment.getUser().getId())
                .build();
    }
}
