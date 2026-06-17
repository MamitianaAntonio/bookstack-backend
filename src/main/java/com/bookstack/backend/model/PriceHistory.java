package com.bookstack.backend.model;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PriceHistory {
  private String id;
  private BigDecimal price;
  private Instant changedAt;
}
