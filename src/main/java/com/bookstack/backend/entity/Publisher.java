package com.bookstack.backend.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Builder
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
  @Builder.Default
  private List<BookCopy> bookCopies = new ArrayList<>();
}
