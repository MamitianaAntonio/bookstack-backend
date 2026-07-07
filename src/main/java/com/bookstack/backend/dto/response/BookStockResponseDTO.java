package com.bookstack.backend.dto.response;

import com.bookstack.backend.dto.FormatStockDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookStockResponseDTO {
  private String bookId;
  private String title;
  private String isbn;
  private Integer totalStock;
  private List<FormatStockDTO> formats;
}
