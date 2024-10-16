package com.application.petcare.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SizeNotFoundException extends RuntimeException {
    public SizeNotFoundException(String message) {
        super(message);
    }
}
