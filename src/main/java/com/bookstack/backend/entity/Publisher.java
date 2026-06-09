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
public class Publisher {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String name;
  private String email;
  private String website;
  private String location;

  @OneToMany(mappedBy = "publisher")
  private List<BookCopy> bookCopies = new ArrayList<>();
}
