package com.application.petcare.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RacaNotFoundException extends RuntimeException {
    public RacaNotFoundException(String message) {
        super(message);
    }
}
