package com.bookstack.backend.exception;

import com.bookstack.backend.exception.model.ApiException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends ApiException {
  public BadRequestException(String message) {
    super(message, HttpStatus.BAD_REQUEST);
  }
}
