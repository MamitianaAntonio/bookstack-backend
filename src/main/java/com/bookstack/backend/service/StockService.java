package com.bookstack.backend.service;

import com.bookstack.backend.dto.FormatStockDTO;
import com.bookstack.backend.dto.response.BookStockResponseDTO;
import com.bookstack.backend.repository.BookCopyRepository;
import com.bookstack.backend.repository.BookRepository;
import com.bookstack.backend.repository.model.JBook;
import com.bookstack.backend.repository.model.JBookCopy;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StockService {
  private final BookRepository bookRepository;
  private final BookCopyRepository bookCopyRepository;

  public BookStockResponseDTO getBookStock(String bookId) {
    JBook book =
        bookRepository
            .findById(bookId)
            .orElseThrow(() -> new NotFoundException("Book with Id : " + bookId + " not found"));

    List<JBookCopy> bookCopies = bookCopyRepository.findByBookId(bookId);

    List<FormatStockDTO> formatStock =
        bookCopies.stream()
            .map(
                copy -> {
                  Integer stock = bookCopyRepository.calculateTotalStockByBookCopyId(copy.getId());
                  return new FormatStockDTO(
                      copy.getId(),
                      copy.getFormat(),
                      copy.getLanguage(),
                      stock != null ? stock : 0);
                })
            .toList();

    Integer totalStock = bookCopyRepository.calculateTotalStockByBookId(bookId);

    return new BookStockResponseDTO(
        book.getId(),
        book.getTitle(),
        book.getIsbn(),
        totalStock != null ? totalStock : 0,
        formatStock);
  }

  public FormatStockDTO getFormatStock(String bookCopyId) {
    JBookCopy copy =
        bookCopyRepository
            .findById(bookCopyId)
            .orElseThrow(
                () -> new NotFoundException("BookCopy with Id : " + bookCopyId + " not found"));

    Integer stock = bookCopyRepository.calculateTotalStockByBookCopyId(bookCopyId);
    return new FormatStockDTO(
        copy.getId(), copy.getFormat(), copy.getLanguage(), stock != null ? stock : 0);
  }
}
