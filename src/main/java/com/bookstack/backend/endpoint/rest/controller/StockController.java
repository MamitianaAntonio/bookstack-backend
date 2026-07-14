package com.bookstack.backend.endpoint.rest.controller;

import com.bookstack.backend.dto.FormatStockDTO;
import com.bookstack.backend.dto.response.BookStockResponseDTO;
import com.bookstack.backend.dto.response.StockResponseDTO;
import com.bookstack.backend.service.StockService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/low")
  public List<StockResponseDTO> getLowStock(@RequestParam(defaultValue = "3") int threshold) {
    return stockService.getLowStock(threshold);
  }
}
