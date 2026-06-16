package com.bookstack.backend.repository.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Table(name = "customer")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class JCustomer {
  @Id @UuidGenerator private String id;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String address;

  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<JSale> sales;
}
