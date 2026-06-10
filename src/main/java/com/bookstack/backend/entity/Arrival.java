package com.bookstack.backend.entity;

import com.bookstack.backend.enums.Condition;
import jakarta.persistence.*;
import java.security.Timestamp;
import java.util.HashSet;
import java.util.Set;
import lombok.*;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Arrival {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private Timestamp arrivalDate;
  private Condition condition;

  @JoinTable(
      name = "arrival_book_copy",
      joinColumns = @JoinColumn(name = "arrival_id"),
      inverseJoinColumns = @JoinColumn(name = "book_copy_id"))
  @Builder.Default
  private Set<BookCopy> bookCopies = new HashSet<>();
}
