package com.bookstack.backend.dto;

import com.bookstack.backend.enums.Format;
import com.bookstack.backend.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormatStockDTO {
  private String bookCopyId;
  private Format format;
  private Language language;
  private Integer stock;
}
