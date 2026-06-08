package com.bookstack.backend.entity;

import jakarta.persistence.*;
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

  private String title;
  private String summary;
  private String isbn;

  @OneToMany(mappedBy = "book")
  private List<BookCopy> bookCopies = new ArrayList<>();
}
