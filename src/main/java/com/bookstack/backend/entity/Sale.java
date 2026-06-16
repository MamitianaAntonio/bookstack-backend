package com.bookstack.backend.entity;

import com.bookstack.backend.enums.PaymentMethod;
import com.bookstack.backend.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import lombok.*;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(nullable = false)
  private Timestamp saleDate;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private PaymentMethod paymentMethod;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Status status;

  @ManyToMany
  @JoinTable(
      name = "sale_book_copy",
      joinColumns = @JoinColumn(name = "sale_id"),
      inverseJoinColumns = @JoinColumn(name = "book_copy_id"))
  @Builder.Default
  private Set<BookCopy> bookCopies = new HashSet<BookCopy>();

  @NotNull(message = "a sale must be made in name of one customer")
  @ManyToOne
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;
}
