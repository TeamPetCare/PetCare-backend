package com.application.petcare.controller.impl;

import com.application.petcare.controller.TokenController;
import com.application.petcare.infra.security.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TokenControllerImpl implements TokenController {

    private TokenService tokenService;

    @Override
    public ResponseEntity<String> verifyToken(String token) {
        String subject = tokenService.validateToken(token);
        if (subject != null) {
            return ResponseEntity.ok("Token válido para o usuário: " + subject);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido ou expirado");
        }
    }
}
