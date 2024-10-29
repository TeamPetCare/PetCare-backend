package com.application.petcare.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Token", description = "Verifica se um token é valido")
@RequestMapping("/validate-token")
public interface TokenController {
    @Operation(summary = "Verifica um token")
    @GetMapping()
    ResponseEntity<String> verifyToken(@RequestParam String token);
}
