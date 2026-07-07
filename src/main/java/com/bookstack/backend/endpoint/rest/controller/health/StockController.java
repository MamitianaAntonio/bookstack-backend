package com.bookstack.backend.endpoint.rest.controller.health;

import com.bookstack.backend.dto.FormatStockDTO;
import com.bookstack.backend.dto.response.BookStockResponseDTO;
import com.bookstack.backend.service.StockService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stock")
@AllArgsConstructor
public class StockController {
  private final StockService stockService;

  @GetMapping("/book/{bookId}")
  public ResponseEntity<BookStockResponseDTO> getBookStock(@PathVariable String bookId) {
    return ResponseEntity.ok(stockService.getBookStock(bookId));
  }

  @GetMapping("/format/{bookCopyId}")
  public ResponseEntity<FormatStockDTO> getFormatStock(@PathVariable String bookCopyId) {
    return ResponseEntity.ok(stockService.getFormatStock(bookCopyId));
  }
}
