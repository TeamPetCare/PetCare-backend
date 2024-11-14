package com.application.petcare.exceptions;

public class DuplicateEntryFoundException extends RuntimeException {
    public DuplicateEntryFoundException(String message){
        super(message);
    }
}
