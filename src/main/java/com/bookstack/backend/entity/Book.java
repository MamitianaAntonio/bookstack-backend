package com.bookstack.backend.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.*;

@Builder
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
  @Builder.Default
  private List<BookCopy> bookCopies = new ArrayList<>();

  @ManyToMany
  @JoinTable(
      name = "book_genre",
      joinColumns = @JoinColumn(name = "book_id"),
      inverseJoinColumns = @JoinColumn(name = "genre_id"))
  @Builder.Default
  private Set<Genre> genres = new HashSet<>();

  @ManyToMany
  @JoinTable(
      name = "book_author",
      joinColumns = @JoinColumn(name = "book_id"),
      inverseJoinColumns = @JoinColumn(name = "author_id"))
  @Builder.Default
  private Set<Author> authors = new HashSet<>();
}
