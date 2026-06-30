package com.bookstack.backend.model;

import com.bookstack.backend.enums.Format;
import com.bookstack.backend.enums.Language;
import com.bookstack.backend.enums.StockStatus;
import java.util.List;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BookCopy {
  private String id;
  private Format format;
  private Language language;
  private List<PriceHistory> priceHistories;
  private Book book;
  private Publisher publisher;
  private Integer stock;
  private StockStatus stockStatus;
}
