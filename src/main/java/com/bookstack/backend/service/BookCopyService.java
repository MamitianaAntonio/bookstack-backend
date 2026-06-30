package com.bookstack.backend.service;

import com.bookstack.backend.enums.Format;
import com.bookstack.backend.enums.Language;
import com.bookstack.backend.mapper.BookCopyMapper;
import com.bookstack.backend.model.BookCopy;
import com.bookstack.backend.repository.BookCopyRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookCopyService {
  private final BookCopyRepository repository;
  private final BookCopyMapper mapper;
  private final BookService bookService;
  private final PublisherService publisherService;

  public List<BookCopy> findAll() {
    return mapper.toModel(repository.findAll());
  }

  public BookCopy findById(String id) {
    return mapper.toModel(
        repository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("BookCopy with id " + id + " not found")));
  }

  public List<BookCopy> findByFormat(Format format) {
    return mapper.toModel(repository.findByFormat(format));
  }

  public List<BookCopy> findByLanguage(Language language) {
    return mapper.toModel(repository.findByLanguage(language));
  }

  public List<BookCopy> findByBookId(String bookId) {
    return mapper.toModel(repository.findByBookId(bookId));
  }

  public List<BookCopy> findByPublisherId(String publisherId) {
    publisherService.findById(publisherId);
    return mapper.toModel(repository.findByPublisherId(publisherId));
  }

  public List<BookCopy> findByBookIdAndFormat(String bookId, Format format) {
    bookService.findById(bookId);
    return mapper.toModel(repository.findByBookIdAndFormat(bookId, format));
  }

  public BookCopy create(BookCopy bookCopy) {
    return mapper.toModel(repository.save(mapper.toEntity(bookCopy)));
  }

  public List<BookCopy> create(List<BookCopy> bookCopies) {
    return bookCopies.stream().map(this::create).toList();
  }

  public BookCopy update(BookCopy bookCopy) {
    return mapper.toModel(repository.save(mapper.toEntity(bookCopy)));
  }
}
