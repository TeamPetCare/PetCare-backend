package com.application.petcare.controller.impl;

import com.application.petcare.controller.PaymentController;
import com.application.petcare.dto.mercadopago.PixPaymentRequest;
import com.application.petcare.dto.payment.PaymentCreateRequest;
import com.application.petcare.dto.payment.PaymentResponse;
import com.application.petcare.exceptions.ResourceNotFoundException;
import com.application.petcare.repository.UserRepository;
import com.application.petcare.services.PaymentService;
import com.application.petcare.services.PixPaymentService;
import com.mercadopago.resources.payment.Payment;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
public class PaymentControllerImpl implements PaymentController {

    private PixPaymentService pixPaymentService;
    private PaymentService paymentService;

    private UserRepository userRepository;

    public ResponseEntity<PaymentResponse> createPayment(PaymentCreateRequest paymentCreateRequest) {
        return ResponseEntity.status(201).body(paymentService.createPayment(paymentCreateRequest));
    }

    public ResponseEntity<PaymentResponse> getPaymentById(Integer id) {
        return ResponseEntity.ok().body(paymentService.getPaymentById(id));
    }

    public ResponseEntity<List<PaymentResponse>> getAllPayments() {
        return ResponseEntity.ok().body(paymentService.getAllPayments());
    }

    public ResponseEntity<PaymentResponse> updatePayment(Integer id, PaymentCreateRequest paymentCreateRequest) {
        return ResponseEntity.ok().body(paymentService.updatePayment(id, paymentCreateRequest));
    }

    public ResponseEntity<Void> deletePayment(Integer id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }


    public ResponseEntity<Payment> createPixPayment(@RequestBody PixPaymentRequest request, Integer userId) {
        Payment payment = pixPaymentService.createPixPayment(request.getAmount(), request.getEmail(), request.getName(), request.getCpf());
        com.application.petcare.entities.Payment createdPayment = new com.application.petcare.entities.Payment();
        createdPayment.setPaymentDate(LocalDateTime.from(payment.getDateCreated().toInstant()));
        createdPayment.setPriece( payment.getTransactionAmount().doubleValue());
        createdPayment.setUser(userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found")));
        createPayment(mapToPaymentResponse(createdPayment));
        return ResponseEntity.ok(payment);
    }


    public PaymentCreateRequest mapToPaymentResponse (com.application.petcare.entities.Payment payment){
        return PaymentCreateRequest.builder()
                .paymentDate(payment.getPaymentDate())
                .priece(payment.getPriece())
                .userId(payment.getUser().getId()).build();
    }
}
