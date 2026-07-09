package com.bookstack.backend.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleItemRequest {
  private BookCopyRequest bookCopy;
  private Integer quantity;
}
