package com.bookstack.backend.repository.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Table(name = "publisher")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class JPublisher {
  @Id @UuidGenerator private String id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  private String website;

  @Column(nullable = false)
  private String location;

  @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<JBookCopy> bookCopies;
}
