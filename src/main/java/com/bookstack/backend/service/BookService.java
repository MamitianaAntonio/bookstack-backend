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

    public List<Book> create(List<Book> toSave) {
        return mapper.toModel(repository.saveAll(mapper.toEntity(toSave)));
    }

    public Book update(String id, Book book) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book with id " + id + " not found"));
        book.setId(id);
        return mapper.toModel(repository.save(mapper.toEntity(book)));
    }
}
