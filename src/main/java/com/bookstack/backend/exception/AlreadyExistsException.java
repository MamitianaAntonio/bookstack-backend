package com.bookstack.backend.exception;

import com.bookstack.backend.exception.model.ApiException;
import org.springframework.http.HttpStatus;

public class AlreadyExistsException extends ApiException {
  public AlreadyExistsException(String message) {
    super(message, HttpStatus.CONFLICT);
  }
}
