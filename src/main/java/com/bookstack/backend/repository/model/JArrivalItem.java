package com.bookstack.backend.repository.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.UuidGenerator;

@Table(name = "arrival_item")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class JArrivalItem {
  @Id @UuidGenerator private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "arrival_id", nullable = false)
  private JArrival arrival;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_copy_id", nullable = false)
  private JBookCopy bookCopy;

  @Check(constraints = "quantity > 0")
  private int quantity;
}
