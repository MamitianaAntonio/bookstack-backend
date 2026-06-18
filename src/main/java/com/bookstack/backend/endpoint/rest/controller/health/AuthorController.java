package com.bookstack.backend.endpoint.rest.controller.health;

import com.bookstack.backend.model.Author;
import com.bookstack.backend.service.AuthorService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AuthorController {
  private AuthorService authorService;

  @GetMapping("/authors")
  public List<Author> getAuthors() {
    return authorService.findAll();
  }

  @GetMapping("/authors/{id}")
  public Author getAuthorById(@PathVariable String id) {
    return authorService.findById(id);
  }

  @PostMapping("/author")
  public Author createAuthor(@RequestBody Author author) {
    return authorService.create(author);
  }

  @PostMapping("/authors")
  public List<Author> createAuthors(@RequestBody List<Author> authors) {
    return authorService.createList(authors);
  }

  @PutMapping("/authors/{id}")
  public Author updateAuthor(@PathVariable String id, @RequestBody Author author) {
    return authorService.update(id, author);
  }

  @DeleteMapping("/authors/{id}")
  public void deleteAuthor(@PathVariable String id) {
    authorService.delete(id);
  }
}
