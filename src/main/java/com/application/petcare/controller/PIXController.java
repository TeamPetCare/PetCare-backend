package com.application.petcare.controller;

import com.application.petcare.dto.pagamento.pix.PIXCreateRequest;
import com.application.petcare.dto.pagamento.pix.PIXResponse;
import com.application.petcare.entities.PIX;
import com.application.petcare.services.PIXService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/pagamento/pix")
@RequiredArgsConstructor
public class PIXController {

    private final PIXService pixService;

    @PostMapping
    public ResponseEntity<PIXResponse> createPix(@RequestBody PIXCreateRequest pixCreateRequest){
        PIXResponse pixResponse = pixService.createPIX(pixCreateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(pixResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PIXResponse> getPIXById(@PathVariable Integer id){
        PIXResponse pixResponse = pixService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(pixResponse);
    }

    @GetMapping
    public ResponseEntity<List<PIXResponse>> getAllPix(){
        List<PIXResponse> pixes = pixService.findAllPIX();
        return ResponseEntity.status(HttpStatus.OK).body(pixes);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePIX(@PathVariable Integer id){
        pixService.deletePIX(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
