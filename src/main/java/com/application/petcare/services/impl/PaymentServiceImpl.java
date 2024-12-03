package com.application.petcare.services.impl;

import com.application.petcare.dto.payment.PaymentCreateRequest;
import com.application.petcare.dto.payment.PaymentResponse;
import com.application.petcare.entities.Payment;
import com.application.petcare.entities.PaymentModel;
import com.application.petcare.exceptions.ResourceNotFoundException;
import com.application.petcare.repository.PaymentModelRepository;
import com.application.petcare.repository.UserRepository;
import com.application.petcare.services.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private UserRepository userRepository;
    private PaymentModelRepository paymentModelRepository;

    @Override
    public PaymentResponse createPayment(PaymentCreateRequest request) {
        PaymentModel payment = PaymentModel.builder()
                .price(request.getPrice())
                .paymentDate(request.getPaymentDate())
                .paymentId(request.getPaymentId())
                .paymentMethod(request.getPaymentMethod())
                .paymentStatus(request.getPaymentStatus())
                .deletedAt(null)
                .user(userRepository.findById(request.getUserId())
                        .orElseThrow(() -> new ResourceNotFoundException("User not found")))
                .build();
        PaymentModel savedPayment = paymentModelRepository.save(payment);
        return mapToPaymentResponse(savedPayment);
    }

    @Override
    public PaymentResponse updatePayment(Integer id, PaymentCreateRequest request) {
        PaymentModel payment = paymentModelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
        payment.setId(id);
        payment.setPrice(request.getPrice());
        payment.setPaymentDate(request.getPaymentDate());
        payment.setPaymentId(request.getPaymentId());
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setPaymentStatus(request.getPaymentStatus());
        payment.setUser(userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found")));

        PaymentModel updatedPayment = paymentModelRepository.save(payment);
        return mapToPaymentResponse(updatedPayment);
    }

    @Override
    public PaymentResponse getPaymentById(Integer id) {
        PaymentModel payment = paymentModelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
        return mapToPaymentResponse(payment);
    }

    @Override
    public List<PaymentResponse> getAllPayments() {
        return paymentModelRepository.findAllByDeletedAtIsNull().stream().map(this::mapToPaymentResponse).toList();
    }

    @Override
    public void deletePayment(Integer id) {
        PaymentModel payment = paymentModelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
        payment.setDeletedAt(LocalDateTime.now());
        PaymentModel updatedPayment = paymentModelRepository.save(payment);
    }

    public PaymentResponse mapToPaymentResponse(PaymentModel payment){
        return PaymentResponse.builder()
                .id(payment.getId())
                .price(payment.getPrice())
                .paymentDate(payment.getPaymentDate())
                .paymentId(payment.getPaymentId())
                .paymentStatus(payment.getPaymentStatus())
                .paymentMethod(payment.getPaymentMethod())
                .userId(payment.getUser().getId())
                .build();
    }
}
