package com.bookstack.backend.model;

import java.util.List;
import lombok.*;

@Getter
@Setter
@ToString
public final class Customer extends User {
  private String address;

  public Customer(
      String id,
      String firstName,
      String lastName,
      String email,
      String phoneNumber,
      String location,
      List<Sale> sales) {
    super(id, firstName, lastName, email, phoneNumber);
    this.address = location;
  }
}
