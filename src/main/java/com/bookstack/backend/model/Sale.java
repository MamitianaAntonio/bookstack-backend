package com.bookstack.backend.model;

import com.bookstack.backend.enums.PaymentMethod;
import com.bookstack.backend.enums.Status;
import java.time.Instant;
import java.util.List;
import lombok.*;

@Getter
@ToString
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
  private String id;
  private Instant saleDate;
  private PaymentMethod paymentMethod;
  private Status status;
  private Customer customer;
  private List<SaleItem> saleItems;
}
