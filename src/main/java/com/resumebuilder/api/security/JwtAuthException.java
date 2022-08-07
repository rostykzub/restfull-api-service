package com.resumebuilder.api.security;

import org.springframework.http.HttpStatus;

import javax.naming.AuthenticationException;

public class JwtAuthException extends AuthenticationException {

    private HttpStatus httpStatus;

    public JwtAuthException(String explanation, HttpStatus httpStatus) {
        super(explanation);
        this.httpStatus = httpStatus;
    }

    public JwtAuthException(String explanation) {
        super(explanation);
    }
}
