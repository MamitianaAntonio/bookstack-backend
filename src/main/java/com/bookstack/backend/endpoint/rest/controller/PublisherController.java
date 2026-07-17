package com.bookstack.backend.endpoint.rest.controller;

import com.bookstack.backend.model.Publisher;
import com.bookstack.backend.service.PublisherService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class PublisherController {
  private PublisherService publisherService;

  @GetMapping("/publishers")
  List<Publisher> getAll() {
    return publisherService.findAll();
  }

  @GetMapping("/publishers/{id}")
  public Publisher findById(@PathVariable String id) {
    return publisherService.findById(id);
  }

  @PostMapping("/publishers")
  public List<Publisher> create(@RequestBody List<Publisher> toSave) {
    return publisherService.create(toSave);
  }

  @PutMapping("/publishers/{id}")
  public Publisher update(@PathVariable String id, @RequestBody Publisher publisher) {
    return publisherService.update(id, publisher);
  }

  @DeleteMapping("/publishers/{id}")
  public void deleteById(@PathVariable String id) {
    publisherService.delete(id);
  }
}
