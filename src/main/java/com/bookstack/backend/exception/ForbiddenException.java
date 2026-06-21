package com.bookstack.backend.exception;

import com.bookstack.backend.exception.model.ApiException;
import org.springframework.http.HttpStatus;

public class ForbiddenException extends ApiException {
  public ForbiddenException(String message) {
    super(message, HttpStatus.FORBIDDEN);
  }
}
