package com.bookstack.backend.repository.model;

import com.bookstack.backend.enums.PaymentMethod;
import com.bookstack.backend.enums.Status;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Table(name = "sale")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class JSale {
  @Id @UuidGenerator private String id;

  @Column(nullable = false, name = "sale_date")
  private Instant saleDate;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private PaymentMethod paymentMethod;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Status status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id", nullable = false)
  private JCustomer customer;

  @OneToMany(mappedBy = "sale")
  private List<JSaleItem> saleItems;
}
