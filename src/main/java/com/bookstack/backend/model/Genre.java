package com.bookstack.backend.model;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
  private String id;
  private String name;
  private String description;
}
