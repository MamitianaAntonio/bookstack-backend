package com.bookstack.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalTime;
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
public class Library {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String location;

  private LocalTime openingTime;
  private LocalTime closingTime;

  @OneToMany(mappedBy = "library")
  @NotEmpty(message = "A library must have at least one book")
  private List<BookCopy> bookCopies = new ArrayList<>();
}
