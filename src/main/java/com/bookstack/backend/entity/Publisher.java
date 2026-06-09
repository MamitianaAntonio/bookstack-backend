package com.bookstack.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
public class Publisher {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(unique = true)
  private String website;

  @Column(nullable = false)
  private String location;

  @OneToMany(mappedBy = "publisher")
  @NotEmpty(message = "A publisher must have published one book at least")
  private List<BookCopy> bookCopies = new ArrayList<>();
}
