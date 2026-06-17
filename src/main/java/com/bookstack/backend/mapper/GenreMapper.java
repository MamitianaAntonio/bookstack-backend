package com.bookstack.backend.mapper;

import com.bookstack.backend.model.Genre;
import com.bookstack.backend.repository.model.JGenre;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GenreMapper {
  public List<Genre> toModel(List<JGenre> genres) {
    return genres.stream().map(this::toModel).toList();
  }

  public Genre toModel(JGenre jGenre) {
    Genre genre = new Genre();
    genre.setId(jGenre.getId());
    genre.setName(jGenre.getName());
    genre.setDescription(jGenre.getDescription());
    return genre;
  }

  public List<JGenre> toEntity(List<Genre> genres) {
    return genres.stream().map(this::toEntity).toList();
  }

  public JGenre toEntity(Genre genre) {
    JGenre jGenre = new JGenre();
    jGenre.setId(genre.getId());
    jGenre.setName(genre.getName());
    jGenre.setDescription(genre.getDescription());
    return jGenre;
  }
}
