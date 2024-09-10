package com.application.petcare.controller;

import com.application.petcare.dto.pagamento.debito.DebitoCreateRequest;
import com.application.petcare.dto.pagamento.debito.DebitoResponse;
import com.application.petcare.services.DebitoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/pagamento/debito")
@RequiredArgsConstructor
public class DebitoController {

    private final DebitoService debitoService;

    @PostMapping
    public ResponseEntity<DebitoResponse> createDebito(@RequestBody DebitoCreateRequest debitoCreateRequest){
        DebitoResponse debitoResponse = debitoService.createDebito(debitoCreateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(debitoResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DebitoResponse> getDebitoById(@PathVariable Integer id){
        DebitoResponse debitoResponse = debitoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(debitoResponse);
    }

    @GetMapping
    public ResponseEntity<List<DebitoResponse>> getAllDebitos(){
        List<DebitoResponse> debitos = debitoService.findAllDebito();
        return ResponseEntity.status(HttpStatus.OK).body(debitos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDebito(@PathVariable Integer id){
        debitoService.deleteDebito(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
