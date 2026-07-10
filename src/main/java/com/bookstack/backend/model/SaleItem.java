package com.bookstack.backend.model;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SaleItem {
  private String id;
  private BookCopy bookCopy;
  private Integer quantity;
}
