package com.bookstack.backend.dto;

import com.bookstack.backend.enums.PaymentMethod;
import com.bookstack.backend.enums.Status;
import java.time.Instant;
import java.util.List;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleRequest {
  private Instant saleDate;
  private PaymentMethod paymentMethod;
  private Status status;
  private List<SaleItemRequest> items;
}
