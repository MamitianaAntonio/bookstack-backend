package com.bookstack.backend.service;

import com.bookstack.backend.dto.BookRequestDTO;
import com.bookstack.backend.mapper.BookMapper;
import com.bookstack.backend.model.Book;
import com.bookstack.backend.repository.AuthorRepository;
import com.bookstack.backend.repository.BookRepository;
import com.bookstack.backend.repository.GenreRepository;
import com.bookstack.backend.repository.model.JAuthor;
import com.bookstack.backend.repository.model.JBook;
import com.bookstack.backend.repository.model.JGenre;
import java.util.ArrayList;
import java.util.List;
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
  public List<Book> create(List<BookRequestDTO> booksToSave) {
    List<JBook> managedBooks = new ArrayList<>();

    for (BookRequestDTO dto : booksToSave) {
      JBook jBook = new JBook();
      jBook.setTitle(dto.getTitle());
      jBook.setSummary(dto.getSummary());
      jBook.setIsbn(dto.getIsbn());

      if (dto.getAuthorIds() != null && !dto.getAuthorIds().isEmpty()) {
        List<JAuthor> authors = authorRepository.findAllById(dto.getAuthorIds());
        if (authors.size() != dto.getAuthorIds().size()) {
          throw new RuntimeException("Some authors not found");
        }
        jBook.setAuthors(authors);
      }

      if (dto.getGenreIds() != null && !dto.getGenreIds().isEmpty()) {
        List<JGenre> genres = genreRepository.findAllById(dto.getGenreIds());
        if (genres.size() != dto.getGenreIds().size()) {
          throw new RuntimeException("Some genres not found");
        }
        jBook.setGenres(genres);
      }

      managedBooks.add(jBook);
    }
    return mapper.toModel(repository.saveAll(managedBooks));
  }

  @Transactional
  public Book update(String id, BookRequestDTO book) {
    JBook existingBook =
        repository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Book with id " + id + " not found"));

    existingBook.setTitle(book.getTitle());
    existingBook.setSummary(book.getSummary());
    existingBook.setIsbn(book.getIsbn());

    if (book.getAuthorIds() != null) {
      List<JAuthor> authors = authorRepository.findAllById(book.getAuthorIds());
      if (authors.size() != book.getAuthorIds().size()) {
        throw new RuntimeException("Some authors not found");
      }
      existingBook.setAuthors(authors);
    }

    if (book.getGenreIds() != null) {
      List<JGenre> genres = genreRepository.findAllById(book.getGenreIds());
      if (genres.size() != book.getGenreIds().size()) {
        throw new RuntimeException("Some genres not found");
      }
      existingBook.setGenres(genres);
    }

    return mapper.toModel(repository.save(existingBook));
  }

  public void delete(String id) {
    repository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Book with id " + id + " not found"));
    repository.deleteById(id);
  }
}
