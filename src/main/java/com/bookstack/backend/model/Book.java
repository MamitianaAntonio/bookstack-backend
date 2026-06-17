package com.bookstack.backend.model;

import java.util.List;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Book {
  private String id;
  private String title;
  private String summary;
  private String isbn;
  private List<Author> authors;
  private List<Genre> genres;
}
