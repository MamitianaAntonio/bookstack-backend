package com.bookstack.backend.model;

import java.util.List;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@ToString
public final class Customer extends User {
  private String address;

  public Customer(String id, String firstName, String lastName, String email, String phoneNumber, String password, String address) {
    super(id, firstName, lastName, email, phoneNumber, password);
    this.address = address;
  }
}
