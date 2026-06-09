package com.bookstack.backend.entity;

import com.bookstack.backend.enums.Format;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.awt.print.Book;
import java.util.HashSet;
import java.util.Set;
import lombok.*;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookCopy {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Format format;

  @NotNull(message = "BookCopy must belong to a library")
  @ManyToOne
  @JoinColumn(name = "library_id", nullable = false)
  private Library library;

  @NotNull(message = "BookCopy must be linked to a book")
  @ManyToOne
  @JoinColumn(name = "book_id", nullable = false)
  private Book book;

  @NotNull(message = "BookCopy must have a publisher")
  @ManyToOne
  @JoinColumn(name = "publisher_id", nullable = false)
  private Publisher publisher;

  @ManyToMany(mappedBy = "bookCopies")
  @Builder.Default
  private Set<Arrival> arrivals = new HashSet<>();
}
