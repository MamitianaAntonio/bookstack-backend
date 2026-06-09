package com.bookstack.backend.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.*;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(nullable = false, unique = true)
  private String name;

  private String description;

  @ManyToMany(mappedBy = "genres")
  @Builder.Default
  private Set<Book> books = new HashSet<>();
}
