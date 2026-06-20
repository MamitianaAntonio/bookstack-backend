package com.bookstack.backend.exception.model;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ExceptionBody> handleApiException(
            ApiException exception, HttpServletRequest request) {
        return ResponseEntity.status(exception.getStatus().value())
                .body(
                        new ExceptionBody(
                                exception.getStatus().value(),
                                exception.getStatus().getReasonPhrase(),
                                exception.getMessage(),
                                request.getPathInfo(),
                                Instant.now()));
    }
}
