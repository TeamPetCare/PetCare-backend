package com.application.petcare.exceptions;

public class BadRoleException extends RuntimeException{
    public BadRoleException(String message) {
        super(message);
    }
}
