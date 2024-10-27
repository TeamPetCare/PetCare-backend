package com.application.petcare.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateScheduleException extends RuntimeException {
    public DuplicateScheduleException(String message) {
        super(message);
    }
}
