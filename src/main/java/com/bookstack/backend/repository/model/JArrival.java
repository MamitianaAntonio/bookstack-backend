package com.bookstack.backend.repository.model;

import com.bookstack.backend.enums.Condition;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Table(name = "arrival")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class JArrival {
  @Id @UuidGenerator private String id;

  @Column(name = "arrival_time", nullable = false)
  private Instant arrivalTime;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Condition condition;

  @OneToMany(mappedBy = "arrival", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<JArrivalItem> arrivalItems;
}
