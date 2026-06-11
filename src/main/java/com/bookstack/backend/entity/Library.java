package com.bookstack.backend.entity;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import lombok.*;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Library {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String location;

  private LocalTime openingTime;
  private LocalTime closingTime;

  @ManyToMany
  @JoinTable(
      name = "library_book_copy",
      joinColumns = @JoinColumn(name = "library_id"),
      inverseJoinColumns = @JoinColumn(name = "book_copy_id"))
  @Builder.Default
  private Set<BookCopy> bookCopies = new HashSet<BookCopy>();
}
