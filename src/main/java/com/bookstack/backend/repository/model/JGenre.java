package com.bookstack.backend.repository.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Table(name = "genre")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JGenre {
  @Id @UuidGenerator private String id;

  @Column(nullable = false, unique = true)
  private String name;

  private String description;

  @ManyToMany(mappedBy = "genres")
  private List<JBook> books;
}
