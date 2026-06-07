package com.bookstack.backend.entity;

import jakarta.persistence.*;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Library {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String name;
  private String location;
  private LocalTime openingTime;
  private LocalTime closingTime;

  @ManyToMany
  @JoinTable(
      name = "library_book_copy",
      joinColumns = @JoinColumn(name = "library_id"),
      inverseJoinColumns = @JoinColumn(name = "book_copy_id"))
  private List<BookCopy> bookCopies;
}
