package com.bookstack.backend.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.bookstack.backend.dto.FormatStockDTO;
import com.bookstack.backend.dto.response.BookStockResponseDTO;
import com.bookstack.backend.enums.Format;
import com.bookstack.backend.enums.Language;
import com.bookstack.backend.exception.NotFoundException;
import com.bookstack.backend.repository.BookCopyRepository;
import com.bookstack.backend.repository.BookRepository;
import com.bookstack.backend.repository.model.JBook;
import com.bookstack.backend.repository.model.JBookCopy;
import com.bookstack.backend.service.StockService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StockServiceTest {

  @Mock private BookRepository bookRepository;

  @Mock private BookCopyRepository bookCopyRepository;

  @InjectMocks private StockService stockService;

  private String bookId;
  private String bookCopyId;
  private JBook testBook;
  private JBookCopy testBookCopy;

  @BeforeEach
  void setUp() {
    bookId = UUID.randomUUID().toString();
    bookCopyId = UUID.randomUUID().toString();

    testBook = new JBook();
    testBook.setId(bookId);
    testBook.setTitle("The Great Gatsby");
    testBook.setIsbn("978-0-7432-7356-5");

    testBookCopy = new JBookCopy();
    testBookCopy.setId(bookCopyId);
    testBookCopy.setBook(testBook);
    testBookCopy.setFormat(Format.PAPERBACK);
    testBookCopy.setLanguage(Language.ENG);
  }

  @Test
  void getBookStock_shouldReturnStockForAllFormats() {
    when(bookRepository.findById(bookId)).thenReturn(Optional.of(testBook));
    when(bookCopyRepository.findByBookId(bookId)).thenReturn(List.of(testBookCopy));
    when(bookCopyRepository.calculateTotalStockByBookCopyId(bookCopyId)).thenReturn(3);
    when(bookCopyRepository.calculateTotalStockByBookId(bookId)).thenReturn(3);

    BookStockResponseDTO result = stockService.getBookStock(bookId);

    assertNotNull(result);
    assertEquals(bookId, result.getBookId());
    assertEquals("The Great Gatsby", result.getTitle());
    assertEquals(3, result.getTotalStock());
    assertEquals(1, result.getFormats().size());

    FormatStockDTO format = result.getFormats().get(0);
    assertEquals(bookCopyId, format.getBookCopyId());
    assertEquals("The Great Gatsby", format.getBookTitle());
    assertEquals(Format.PAPERBACK, format.getFormat());
    assertEquals(Language.ENG, format.getLanguage());
    assertEquals(3, format.getStock());
  }

  @Test
  void getBookStock_shouldThrowException_whenBookNotFound() {
    String wrongId = "wrongId";
    when(bookRepository.findById(wrongId)).thenReturn(Optional.empty());

    NotFoundException exception =
        assertThrows(NotFoundException.class, () -> stockService.getBookStock(wrongId));
  }

  @Test
  void getFormatStock_shouldReturnStockForSpecificFormat() {
    when(bookCopyRepository.findById(bookCopyId)).thenReturn(Optional.of(testBookCopy));
    when(bookCopyRepository.calculateTotalStockByBookCopyId(bookCopyId)).thenReturn(3);

    FormatStockDTO result = stockService.getFormatStock(bookCopyId);

    assertNotNull(result);
    assertEquals(bookCopyId, result.getBookCopyId());
    assertEquals("The Great Gatsby", result.getBookTitle());
    assertEquals(Format.PAPERBACK, result.getFormat());
    assertEquals(Language.ENG, result.getLanguage());
    assertEquals(3, result.getStock());
  }

  @Test
  void getFormatStock_shouldThrowException_whenBookCopyNotFound() {
    String wrongId = "wrongId";
    when(bookCopyRepository.findById(wrongId)).thenReturn(Optional.empty());

    NotFoundException exception =
        assertThrows(NotFoundException.class, () -> stockService.getFormatStock(wrongId));
  }
}
