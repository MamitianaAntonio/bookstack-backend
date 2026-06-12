package com.bookstack.backend.repository.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Table(name = "price_history")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class JPriceHistory {
  @Id @UuidGenerator private String id;

  @Column(nullable = false)
  private BigDecimal price;

  @Column(nullable = false, name = "changed_at")
  private Instant changedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_copy_id", nullable = false)
  private JBookCopy bookCopy;
}
