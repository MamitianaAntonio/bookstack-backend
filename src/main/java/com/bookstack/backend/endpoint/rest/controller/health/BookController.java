package com.bookstack.backend.endpoint.rest.controller.health;

import com.bookstack.backend.model.Book;
import com.bookstack.backend.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BookController {
    private final BookService service;

    @GetMapping("/books")
    public List<Book> getAll() {
        return service.findAll();
    }

    @GetMapping("/books/{id}")
    public Book getById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping("/books")
    public List<Book> create(@RequestBody List<Book> toSave) {
        return service.create(toSave);
    }
}
