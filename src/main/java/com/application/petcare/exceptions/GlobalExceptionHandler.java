package com.application.petcare.exceptions;

import com.application.petcare.dto.exception.ErrorDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // Lida com exceção de recurso não encontrado
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.error("Resource not found: {}", ex.getMessage());
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), "Resource not found");
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // Lida com exceção de requisição inválida
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDetails> handleBadRequestException(BadRequestException ex) {
        log.error("Bad request: {}", ex.getMessage());
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), "Invalid request data");
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // Lida com outras exceções genéricas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex) {
        log.error("An unexpected error occurred: {}", ex.getMessage());
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), "Unexpected error occurred");
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
