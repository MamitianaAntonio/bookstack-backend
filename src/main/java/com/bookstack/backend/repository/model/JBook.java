package com.bookstack.backend.repository.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "book")
@NoArgsConstructor
@AllArgsConstructor
public class JBook {
  @Id @UuidGenerator private String id;

  @Column(nullable = false)
  private String title;

  private String summary;

  @Column(nullable = false)
  private String isbn;

  @ManyToMany
  @JoinTable(
      name = "book_author",
      joinColumns = @JoinColumn(name = "book_id"),
      inverseJoinColumns = @JoinColumn(name = "author_id"))
  private List<JAuthor> authors;

  @ManyToMany
  @JoinTable(
      name = "book_genre",
      joinColumns = @JoinColumn(name = "book_id"),
      inverseJoinColumns = @JoinColumn(name = "genre_id"))
  private List<JGenre> genres;

  @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<JBookCopy> bookCopies;
}
