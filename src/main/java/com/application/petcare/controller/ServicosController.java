package com.application.petcare.controller;

import com.application.petcare.dto.servicos.ServicosCreateRequest;
import com.application.petcare.dto.servicos.ServicosResponse;
import com.application.petcare.dto.servicos.ServicosUpdateRequest;
import com.application.petcare.services.ServicosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicos")
@RequiredArgsConstructor
public class ServicosController {
    private final ServicosService servicosService;

    @PostMapping
    public ResponseEntity<ServicosResponse> createServico(@Valid @RequestBody ServicosCreateRequest request){
        return ResponseEntity.ok(servicosService.createServico(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicosResponse> updateServico
            (@PathVariable Integer id,
             @Valid @RequestBody ServicosUpdateRequest request){
        return ResponseEntity.ok(servicosService.updateServico(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteServico(@PathVariable Integer id){
        servicosService.deleteServico(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ServicosResponse>> getAllServicos(){
        List<ServicosResponse> servicos = servicosService.findAllServicos();
        return ResponseEntity.status(HttpStatus.OK).body(servicos);
    }

}
