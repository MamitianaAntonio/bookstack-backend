package com.bookstack.backend.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(nullable = false)
  private String firstName;

  private String lastName;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(unique = true)
  private String phone;

  @OneToMany(mappedBy = "customer")
  @Builder.Default
  private List<Sale> sales = new ArrayList<>();
}
