package com.bookstack.backend.dto;

import com.bookstack.backend.enums.Format;
import com.bookstack.backend.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FormatStockDTO {
  private String bookCopyId;
  private String bookTitle;
  private Format format;
  private Language language;
  private Integer stock;
}
