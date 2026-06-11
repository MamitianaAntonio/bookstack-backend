package com.bookstack.backend.entity;

import com.bookstack.backend.enums.Format;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.awt.print.Book;
import java.math.BigDecimal;
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

  @Column(nullable = false)
  private BigDecimal price;

  @ManyToMany(mappedBy = "bookCopies")
  private Set<Library> libraries = new HashSet<>();

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

  @ManyToMany(mappedBy = "bookCopies")
  @Builder.Default
  private Set<Sale> sales = new HashSet<>();
}
