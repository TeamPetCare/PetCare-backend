package com.application.petcare.controller;


import com.application.petcare.dto.mercadopago.PixPaymentRequest;
import com.application.petcare.services.PixPaymentService;
import com.mercadopago.resources.payment.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PixPaymentService pixPaymentService;

    @PostMapping("/pix")
    public ResponseEntity<Payment> createPixPayment(@RequestBody PixPaymentRequest request) {
        Payment payment = pixPaymentService.createPixPayment(request.getAmount(), request.getEmail(), request.getName(), request.getCpf());
        return ResponseEntity.ok(payment);
    }
}
