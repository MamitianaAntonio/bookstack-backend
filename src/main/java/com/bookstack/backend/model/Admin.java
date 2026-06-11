package com.bookstack.backend.model;

import com.bookstack.backend.enums.Role;
import lombok.*;

@Getter
@Setter
@ToString
public final class Admin extends User {
  private Role role;

  public Admin(
      String id, String firstName, String lastName, String email, String phoneNumber, Role role) {
    super(id, firstName, lastName, email, phoneNumber);
    this.role = role;
  }
}
