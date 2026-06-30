package com.bookstack.backend.endpoint.rest.controller.health;

import com.bookstack.backend.model.BookCopy;
import com.bookstack.backend.service.BookCopyService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class BookCopyController {
  private BookCopyService bookCopyService;

  @GetMapping("/book-copy")
  public List<BookCopy> findAll() {
    return bookCopyService.findAll();
  }

  @GetMapping("/book-copy/{id}")
  public BookCopy findById(@PathVariable String id) {
    return bookCopyService.findById(id);
  }

  @PostMapping("/book-copy")
  public List<BookCopy> create(@RequestBody List<BookCopy> bookCopies) {
    return bookCopyService.create(bookCopies);
  }
}
