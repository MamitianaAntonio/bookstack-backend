package com.bookstack.backend.entity;

import jakarta.persistence.*;
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

  private String name;
  private String location;
  private LocalTime openingTime;
  private LocalTime closingTime;

  @OneToMany(mappedBy = "library")
  private List<BookCopy> bookCopies = new ArrayList<>();
}
