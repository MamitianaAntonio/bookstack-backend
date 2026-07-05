package com.bookstack.backend.dto.response;

import com.bookstack.backend.enums.StockStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StockResponseDTO {
  private String bookCopyId;
  private String bookCopyTitle;
  private int stock;
  private StockStatus status;
}
