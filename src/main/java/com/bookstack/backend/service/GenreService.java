package com.bookstack.backend.service;

import com.bookstack.backend.mapper.GenreMapper;
import com.bookstack.backend.model.Genre;
import com.bookstack.backend.repository.GenreRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GenreService {
  private final GenreRepository repository;
  private final GenreMapper mapper;

  public List<Genre> findAll() {
    return mapper.toModel(repository.findAll());
  }

  public Genre findById(String id) {
    return mapper.toModel(
        repository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Genre with id " + id + " not found")));
  }

  public List<Genre> create(List<Genre> genres) {
    return mapper.toModel(repository.saveAll(mapper.toEntity(genres)));
  }

  public Genre update(String id, Genre genre) {
    repository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Genre with id " + id + " not found"));
    genre.setId(id);
    return mapper.toModel(repository.save(mapper.toEntity(genre)));
  }

  public void delete(String id) {
    repository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Genre with id " + id + " not found"));
    repository.deleteById(id);
  }
}
