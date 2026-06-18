package com.bookstack.backend.endpoint.rest.controller.health;

import com.bookstack.backend.model.Author;
import com.bookstack.backend.service.AuthorService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthorController {
  private AuthorService authorService;

  @GetMapping("/authors")
  public List<Author> getAuthors() {}
}
