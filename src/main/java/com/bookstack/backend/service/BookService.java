package com.bookstack.backend.service;

import com.bookstack.backend.mapper.BookMapper;
import com.bookstack.backend.model.Book;
import com.bookstack.backend.repository.AuthorRepository;
import com.bookstack.backend.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bookstack.backend.repository.GenreRepository;
import com.bookstack.backend.repository.model.JAuthor;
import com.bookstack.backend.repository.model.JBook;
import com.bookstack.backend.repository.model.JGenre;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BookService {
  private final BookRepository repository;
  private final AuthorRepository authorRepository;
  private final GenreRepository genreRepository;
  private final BookMapper mapper;

  public List<Book> findAll() {
    return mapper.toModel(repository.findAll());
  }

  public Book findById(String id) {
    return mapper.toModel(
        repository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Book with id " + id + " not found")));
  }

  @Transactional
  public List<Book> create(List<Book> booksToSave) {
    List<JBook> jBooks = mapper.toEntity(booksToSave);
    List<JBook> managedBooks = new ArrayList<>();

    for (JBook jBook : jBooks) {
      if (jBook.getAuthors() != null) {
        List<JAuthor> managedAuthors = new ArrayList<>();
        for (JAuthor author : jBook.getAuthors()) {
          Optional<JAuthor> existingAuthor = authorRepository.findByEmail(author.getEmail());
          if (existingAuthor.isPresent()) {
            managedAuthors.add(existingAuthor.get());
          } else {
            JAuthor savedAuthor = authorRepository.save(author);
            managedAuthors.add(savedAuthor);
          }
        }
        jBook.setAuthors(managedAuthors);
      }

      if (jBook.getGenres() != null) {
        List<JGenre> managedGenres = new ArrayList<>();
        for (JGenre genre : jBook.getGenres()) {
          Optional<JGenre> existingGenre = genreRepository.findByName(genre.getName());
          if (existingGenre.isPresent()) {
            managedGenres.add(existingGenre.get());
          } else {
            JGenre savedGenre = genreRepository.save(genre);
            managedGenres.add(savedGenre);
          }
        }
        jBook.setGenres(managedGenres);
      }

      managedBooks.add(jBook);
    }
    return mapper.toModel(repository.saveAll(managedBooks));
  }

  public Book update(String id, Book book) {
    repository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Book with id " + id + " not found"));
    book.setId(id);
    return mapper.toModel(repository.save(mapper.toEntity(book)));
  }

  public void delete(String id) {
    repository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Book with id " + id + " not found"));
    repository.deleteById(id);
  }
}
