package com.bookstack.backend.model;

import com.bookstack.backend.enums.Role;
import lombok.*;

@Getter
@Setter
@ToString
public final class Admin extends User {
  private Role role;

  public Admin(
      String id,
      String firstName,
      String lastName,
      String email,
      String phoneNumber,
      String password,
      Role role) {
    super(id, firstName, lastName, email, phoneNumber, password);
    this.role = role;
  }
}
