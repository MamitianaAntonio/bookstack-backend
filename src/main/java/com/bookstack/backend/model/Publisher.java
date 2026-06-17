package com.bookstack.backend.model;

import java.util.List;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Publisher {
  private String id;
  private String name;
  private String email;
  private String website;
  private String location;
  private List<BookCopy> bookCopies;
}
