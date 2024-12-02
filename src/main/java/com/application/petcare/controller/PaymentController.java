package com.application.petcare.controller;



import com.application.petcare.dto.mercadopago.PixPaymentRequest;
import com.application.petcare.dto.payment.PaymentCreateRequest;
import com.application.petcare.dto.payment.PaymentResponse;
import com.mercadopago.resources.payment.Payment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@Tag(name = "Payments", description = "Gerenciar pagamentos")
@RequestMapping("/api/payments")
public interface PaymentController {

    @Operation(summary = "Criar um novo pagamento")
    @PostMapping
    ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentCreateRequest request);

    @Operation(summary = "Atualizar um pagamento existente")
    @PutMapping("/{id}")
    ResponseEntity<PaymentResponse> updatePayment(@PathVariable Integer id, @RequestBody PaymentCreateRequest request);

    @Operation(summary = "Buscar um pagamento pelo ID")
    @GetMapping("/{id}")
    ResponseEntity<PaymentResponse> getPaymentById(@PathVariable Integer id);

    @Operation(summary = "Listar todos os pagamentos")
    @GetMapping
    ResponseEntity<List<PaymentResponse>> getAllPayments();

    @Operation(summary = "Deletar um pagamento")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePayment(@PathVariable Integer id);

    @Operation(summary = "Criar um novo pagamento pix")
    @PostMapping("/pix/{userId}")
    ResponseEntity<Payment> createPixPayment(@RequestBody PixPaymentRequest request, @PathVariable Integer userId);
}
