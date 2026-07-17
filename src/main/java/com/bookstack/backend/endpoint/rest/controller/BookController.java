package com.bookstack.backend.endpoint.rest.controller;

import com.bookstack.backend.dto.BookInfoDTO;
import com.bookstack.backend.dto.request.BookRequestDTO;
import com.bookstack.backend.model.Book;
import com.bookstack.backend.service.BookExternalService;
import com.bookstack.backend.service.BookService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class BookController {
  private final BookService service;
  private final BookExternalService bookExternalService;

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/books")
  public List<Book> getAll() {
    return service.findAll();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/books/{id}")
  public Book getById(@PathVariable String id) {
    return service.findById(id);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/books")
  public List<Book> create(@RequestBody List<BookRequestDTO> toSave) {
    return service.create(toSave);
  }

  @PutMapping("/books/{id}")
  public Book update(@PathVariable String id, @RequestBody BookRequestDTO book) {
    return service.update(id, book);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/books/{id}")
  public void delete(@PathVariable String id) {
    service.delete(id);
  }

  @GetMapping("/books/search")
  public ResponseEntity<BookInfoDTO> search(@RequestParam String isbn) {
    String cleanedIsbn = isbn.replaceAll("[\\s-]", "");

    BookInfoDTO bookInfo = bookExternalService.getBookInfoDTO(cleanedIsbn);

    return ResponseEntity.ok(bookInfo);
  }
}
