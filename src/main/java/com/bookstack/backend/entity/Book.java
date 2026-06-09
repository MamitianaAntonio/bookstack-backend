package com.bookstack.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String summary;

  @Column(nullable = false, unique = true)
  private String isbn;

  @OneToMany(mappedBy = "book")
  private List<BookCopy> bookCopies = new ArrayList<>();

  @ManyToMany
  @JoinTable(
      name = "book_genre",
      joinColumns = @JoinColumn(name = "book_id"),
      inverseJoinColumns = @JoinColumn(name = "genre_id"))
  @NotEmpty(message = "Book must have at least one genre")
  private List<Genre> genres = new ArrayList<>();
}
