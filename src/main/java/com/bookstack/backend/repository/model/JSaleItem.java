package com.bookstack.backend.repository.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.UuidGenerator;

@Table(name = "sale_item")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Getter
public class JSaleItem {
  @Id @UuidGenerator private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sale_id", nullable = false)
  private JSale sale;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_copy_id", nullable = false)
  private JBookCopy bookCopy;

  @Check(constraints = "quantity > 0")
  private int quantity;
}
