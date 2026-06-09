package com.bookstack.backend.entity;

import com.bookstack.backend.enums.Format;
import jakarta.persistence.*;
import java.awt.print.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
  private Format format;

  @ManyToOne
  @JoinColumn(name = "library_id")
  private Library library;

  @ManyToOne
  @JoinColumn(name = "book_id")
  private Book book;

  @ManyToOne
  @JoinColumn(name = "publisher_id")
  private Publisher publisher;
}
