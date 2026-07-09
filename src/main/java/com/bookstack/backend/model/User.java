package com.bookstack.backend.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@SuperBuilder
public abstract sealed class User permits Admin, Customer {
  private final String id;
  private final String firstName;
  private final String lastName;
  private final String email;
  private final String phoneNumber;
  private final String password;
}
