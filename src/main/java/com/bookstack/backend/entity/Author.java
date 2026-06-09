package com.bookstack.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.*;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false, unique = true)
  private String nickname;

  @Column(nullable = false)
  private String biography;

  private String nationality;

  @Column(nullable = false)
  private LocalDate dateOfBirth;

  private LocalDate dateOfDeath;
  private String email;
  private String website;

  @ManyToMany(mappedBy = "authors")
  @Builder.Default
  private Set<Book> books = new HashSet<>();
}
