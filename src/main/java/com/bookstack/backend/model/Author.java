package com.bookstack.backend.model;

import com.bookstack.backend.enums.Language;
import java.util.List;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {
  private String id;
  private String firstName;
  private String lastName;
  private String biography;
  private Language language;
  private String email;
  private List<Book> books;
}
