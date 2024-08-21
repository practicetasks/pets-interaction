package com.practice.exceptionhandlers.exception;

public class IncorrectCountException extends RuntimeException {
    public IncorrectCountException(String message) {
        super(message);
    }
}
