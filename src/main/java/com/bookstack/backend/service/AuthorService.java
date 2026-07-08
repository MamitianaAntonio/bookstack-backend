package com.bookstack.backend.service;

import com.bookstack.backend.exception.NotFoundException;
import com.bookstack.backend.mapper.AuthorMapper;
import com.bookstack.backend.model.Author;
import com.bookstack.backend.repository.AuthorRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorService {
  private final AuthorRepository repository;
  private final AuthorMapper mapper;

  public List<Author> findAll() {
    return mapper.toModel(repository.findAll());
  }

  public Author findById(String id) {
    return mapper.toModel(
        repository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Author with id " + id + " not found")));
  }

  public List<Author> create(List<Author> toSave) {
    return mapper.toModel(repository.saveAll(mapper.toEntity(toSave)));
  }

  public Author update(String id, Author author) {
    repository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Author with id " + id + " not found"));
    author.setId(id);
    return mapper.toModel(repository.save(mapper.toEntity(author)));
  }

  public void delete(String id) {
    repository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Author with id " + id + " not found"));
    repository.deleteById(id);
  }
}
