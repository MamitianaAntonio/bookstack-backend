package com.bookstack.backend.endpoint.rest.controller;

import com.bookstack.backend.model.Author;
import com.bookstack.backend.service.AuthorService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AuthorController {
  private final AuthorService service;

  @GetMapping("/authors")
  public List<Author> getAll() {
    return service.findAll();
  }

  @GetMapping("/authors/{id}")
  public Author getById(@PathVariable String id) {
    return service.findById(id);
  }

  @PostMapping("/authors")
  public List<Author> create(@RequestBody List<Author> toSave) {
    return service.create(toSave);
  }

  @PutMapping("/authors/{id}")
  public Author update(@PathVariable String id, @RequestBody Author toUpdate) {
    return service.update(id, toUpdate);
  }

  @DeleteMapping("/authors/{id}")
  public void delete(@PathVariable String id) {
    service.delete(id);
  }
}
