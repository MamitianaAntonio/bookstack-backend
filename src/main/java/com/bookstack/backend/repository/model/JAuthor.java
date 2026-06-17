package com.bookstack.backend.repository.model;

import com.bookstack.backend.enums.Language;
import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "author")
@NoArgsConstructor
@AllArgsConstructor
public class JAuthor {
  @Id @UuidGenerator private String id;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  private String biography;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Language language;

  private String email;

  @ManyToMany(mappedBy = "authors")
  private List<JBook> books;
}
