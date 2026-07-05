package com.bookstack.backend.service;

import com.bookstack.backend.dto.BookCopyRequestDTO;
import com.bookstack.backend.dto.response.StockResponseDTO;
import com.bookstack.backend.enums.Format;
import com.bookstack.backend.enums.Language;
import com.bookstack.backend.enums.StockStatus;
import com.bookstack.backend.exception.NotFoundException;
import com.bookstack.backend.mapper.BookCopyMapper;
import com.bookstack.backend.model.BookCopy;
import com.bookstack.backend.repository.*;
import com.bookstack.backend.repository.model.JBook;
import com.bookstack.backend.repository.model.JBookCopy;
import com.bookstack.backend.repository.model.JPublisher;
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
  private final BookRepository bookRepository;
  private final PublisherRepository publisherRepository;
  private final ArrivalItemRepository arrivalItemRepository;
  private final SaleItemRepository saleItemRepository;

  private static final int LOW_STOCK_THRESHOLD = 5;

  public List<BookCopy> findAll() {
    return mapper.toModel(repository.findAll());
  }

  public BookCopy findById(String id) {
    return mapper.toModel(
        repository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("BookCopy with id " + id + " not found")));
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

  public BookCopy create(BookCopyRequestDTO dto) {
    JBook book =
        bookRepository
            .findById(dto.getBookId())
            .orElseThrow(
                () -> new NotFoundException("Book with id " + dto.getBookId() + " not found"));
    JPublisher publisher =
        publisherRepository
            .findById(dto.getPublisherId())
            .orElseThrow(
                () ->
                    new NotFoundException(
                        "Publisher with id " + dto.getPublisherId() + " not found"));

    JBookCopy jBookCopy = new JBookCopy();
    jBookCopy.setFormat(dto.getFormat());
    jBookCopy.setLanguage(dto.getLanguage());
    jBookCopy.setBook(book);
    jBookCopy.setPublisher(publisher);

    return mapper.toModel(repository.save(jBookCopy));
  }

  public List<BookCopy> create(List<BookCopyRequestDTO> bookCopies) {
    return bookCopies.stream().map(this::create).toList();
  }

  public BookCopy update(BookCopy bookCopy) {
    return mapper.toModel(repository.save(mapper.toEntity(bookCopy)));
  }

  public int calculateStock(String bookCopyId) {
    int arrived = arrivalItemRepository.sumQuantityByBookCopyId(bookCopyId);
    int sold = saleItemRepository.sumQuantityByBookCopyId(bookCopyId);
    return arrived - sold;
  }

  public StockStatus calculateStatus(int stock) {
    if (stock <= 0) return StockStatus.OUT_OF_STOCK;
    if (stock <= LOW_STOCK_THRESHOLD) return StockStatus.LOW;
    return StockStatus.AVAILABLE;
  }

  public StockResponseDTO getStock(String bookCopyId) {
    JBookCopy jBookCopy =
        repository
            .findById(bookCopyId)
            .orElseThrow(
                () -> new NotFoundException("BookCopy with id " + bookCopyId + " not found"));
    String bookCopyTitle = jBookCopy.getBook().getTitle();

    int stock = calculateStock(bookCopyId);
    StockStatus status = calculateStatus(stock);
    return new StockResponseDTO(bookCopyId, bookCopyTitle, stock, status);
  }
}
