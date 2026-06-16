package com.bookstack.backend.service;

import com.bookstack.backend.mapper.BookMapper;
import com.bookstack.backend.model.Book;
import com.bookstack.backend.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository repository;
    private final BookMapper mapper;

    public List<Book> findAll() {
        return mapper.toModel(repository.findAll());
    }

    public Book findById(String id) {
        return mapper.toModel(
                repository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Book with id " + id + " not found")));
    }
}
