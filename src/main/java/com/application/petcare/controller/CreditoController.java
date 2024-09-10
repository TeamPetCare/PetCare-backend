package com.application.petcare.controller;

import com.application.petcare.dto.pagamento.credito.CreditoCreateRequest;
import com.application.petcare.dto.pagamento.credito.CreditoResponse;
import com.application.petcare.services.CreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/pagamento/credito")
@RequiredArgsConstructor
public class CreditoController {

    private final CreditoService creditoService;

    @PostMapping
    public ResponseEntity<CreditoResponse> createCredito(@RequestBody CreditoCreateRequest creditoCreateRequest){
        CreditoResponse creditoResponse = creditoService.createCredito(creditoCreateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(creditoResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditoResponse> getCreditoById(@PathVariable Integer id){
        CreditoResponse creditoResponse = creditoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(creditoResponse);
    }

    @GetMapping
    public ResponseEntity<List<CreditoResponse>> getAllCreditos(){
        List<CreditoResponse> creditos = creditoService.findAllCredito();
        return ResponseEntity.status(HttpStatus.OK).body(creditos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCredito(@PathVariable Integer id){
        creditoService.deleteCredito(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
