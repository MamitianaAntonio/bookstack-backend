package com.bookstack.backend.exception.model;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class ApiException extends RuntimeException {
    private final HttpStatus status;

    public ApiException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
