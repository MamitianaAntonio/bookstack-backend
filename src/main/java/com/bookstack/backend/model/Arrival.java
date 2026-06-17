package com.bookstack.backend.model;

import com.bookstack.backend.enums.Condition;
import java.time.Instant;
import java.util.List;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Arrival {
  private String id;
  private Instant arrivalTime;
  private Condition condition;
  private List<ArrivalItem> arrivalItems;
}
