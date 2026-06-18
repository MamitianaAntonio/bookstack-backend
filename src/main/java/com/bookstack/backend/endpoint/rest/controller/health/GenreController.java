package com.bookstack.backend.endpoint.rest.controller.health;

import com.bookstack.backend.model.Genre;
import com.bookstack.backend.service.GenreService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class GenreController {
  private final GenreService service;

  @GetMapping("/genres")
  public List<Genre> getAll() {
    return service.findAll();
  }

  @GetMapping("/genres/{id}")
  public Genre getById(@PathVariable String id) {
    return service.findById(id);
  }

  @PostMapping("/genres")
  public List<Genre> create(@RequestBody List<Genre> toCreate) {
    return service.create(toCreate);
  }

  @PutMapping("/genres/{id}")
  public Genre update(@PathVariable String id, @RequestBody Genre toUpdate) {
    return service.update(id, toUpdate);
  }

  @DeleteMapping("/genres/{id}")
  public void delete(@PathVariable String id) {
    service.delete(id);
  }
}
