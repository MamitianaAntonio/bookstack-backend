package com.bookstack.backend.model;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
  private String id;
  private String name;
  private String description;
}
