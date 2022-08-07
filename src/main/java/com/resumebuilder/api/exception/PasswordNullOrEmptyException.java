package com.resumebuilder.api.exception;

public class PasswordNullOrEmptyException extends Exception{
    public PasswordNullOrEmptyException(String message) {
        super(message);
    }
}
