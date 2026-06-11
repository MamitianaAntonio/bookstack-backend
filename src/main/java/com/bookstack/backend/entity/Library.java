package com.bookstack.backend.entity;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Builder
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
  @Builder.Default
  private List<BookCopy> bookCopies = new ArrayList<>();
}
