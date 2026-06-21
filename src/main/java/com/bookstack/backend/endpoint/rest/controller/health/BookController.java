package com.bookstack.backend.endpoint.rest.controller.health;

import com.bookstack.backend.model.Book;
import com.bookstack.backend.service.BookService;
import java.util.List;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class BookController {
  private final BookService service;

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
  public List<Book> create(@RequestBody List<Book> toSave) {
    return service.create(toSave);
  }

  @PutMapping("/books/{id}")
  public Book update(@PathVariable String id, @RequestBody Book book) {
    return service.update(id, book);
  }

  @DeleteMapping("/books/{id}")
  public void delete(@PathVariable String id) {
    service.delete(id);
  }
}
