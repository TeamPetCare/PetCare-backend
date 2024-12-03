package com.application.petcare.controller.impl;

import com.application.petcare.controller.PaymentController;
import com.application.petcare.dto.mercadopago.MercadoPagoResponse;
import com.application.petcare.dto.mercadopago.PixPaymentRequest;
import com.application.petcare.dto.payment.PaymentCreateRequest;
import com.application.petcare.dto.payment.PaymentResponse;
import com.application.petcare.entities.PaymentModel;
import com.application.petcare.entities.User;
import com.application.petcare.exceptions.ResourceNotFoundException;
import com.application.petcare.repository.UserRepository;
import com.application.petcare.services.PaymentService;
import com.application.petcare.services.PixPaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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


    public ResponseEntity<PaymentModel> createPixPayment(@RequestBody PixPaymentRequest request, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return ResponseEntity.ok(pixPaymentService.createPixPayment(request.getAmount(), user));
    }
    public ResponseEntity<Void> mercadoPagoApiCallBack(@RequestBody MercadoPagoResponse mercadoPagoResponse) {
        System.out.println("CALLBACK");
        pixPaymentService.checkPayment(mercadoPagoResponse);
        return ResponseEntity.noContent().build();
    }


    public PaymentCreateRequest mapToPaymentResponse (com.application.petcare.entities.Payment payment){
        return PaymentCreateRequest.builder()
                .paymentDate(payment.getPaymentDate())
                .price(payment.getPrice())
                .userId(payment.getUser().getId()).build();
    }
}
