package com.bookstack.backend.endpointControllertest;

import static org.mockito.Mockito.when;

import com.bookstack.backend.dto.FormatStockDTO;
import com.bookstack.backend.dto.response.BookStockResponseDTO;
import com.bookstack.backend.dto.response.StockResponseDTO;
import com.bookstack.backend.endpoint.rest.controller.StockController;
import com.bookstack.backend.enums.Format;
import com.bookstack.backend.enums.Language;
import com.bookstack.backend.enums.StockStatus;
import com.bookstack.backend.exception.GlobalExceptionHandler;
import com.bookstack.backend.exception.NotFoundException;
import com.bookstack.backend.service.BookService;
import com.bookstack.backend.service.StockService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest({StockController.class, GlobalExceptionHandler.class})
public class StockControllerTest {
  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private StockService stockService;

  @MockBean private BookService bookService;

  private BookStockResponseDTO bookStockResponseDTO;
  private FormatStockDTO formatStockDTO;
  private String bookId;
  private String bookCopyId;

  @BeforeEach
  void setup() {
    bookId = UUID.randomUUID().toString();
    bookCopyId = UUID.randomUUID().toString();

    FormatStockDTO format1 =
        new FormatStockDTO(bookCopyId, "The Great Gatsby", Format.PAPERBACK, Language.ENG, 3);

    FormatStockDTO format2 =
        new FormatStockDTO(
            UUID.randomUUID().toString(), "The Great Gatsby", Format.HARDBACK, Language.ENG, 12);

    bookStockResponseDTO =
        new BookStockResponseDTO(
            bookId, "The Great Gatsby", "978-0743273565", 15, List.of(format1, format2));

    formatStockDTO =
        new FormatStockDTO(bookCopyId, "The Great Gatsby", Format.HARDBACK, Language.ENG, 3);
  }

  @Test
  void getBookStock_shouldReturn_OK() throws Exception {
    when(stockService.getBookStock(bookId)).thenReturn(bookStockResponseDTO);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/stock/book/" + bookId))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.bookId").value(bookId))
        .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("The Great Gatsby"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value("978-0743273565"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.totalStock").value(15));
  }

  @Test
  void getBookStock_shouldReturn_NotFound() throws Exception {
    String wrongId = "wrongId";
    when(stockService.getBookStock(wrongId)).thenThrow(new NotFoundException("Book not found"));

    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/stock/book/" + wrongId))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Book not found"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("404"));
  }

  @Test
  void getFormatStock_shouldReturn_OK() throws Exception {
    when(stockService.getFormatStock(bookCopyId)).thenReturn(formatStockDTO);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/stock/format/" + bookCopyId))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.bookCopyId").value(bookCopyId))
        .andExpect(MockMvcResultMatchers.jsonPath("$.bookTitle").value("The Great Gatsby"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.format").value("HARDBACK"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.language").value("ENG"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.stock").value(3));
  }

  @Test
  void getFormatStock_shouldReturn_Error_whenBookCopyNotFound() throws Exception {
    String wrongId = "wrongId";
    when(stockService.getFormatStock(wrongId))
        .thenThrow(new NotFoundException("Book copy not found"));

    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/stock/format/" + wrongId))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Book copy not found"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("404"));
  }

  @Test
  void getLowStock_shouldReturn_OK_withDefaultThreshold() throws Exception {
    StockResponseDTO lowStockItem =
        new StockResponseDTO(bookCopyId, "The Great Gasby", 2, StockStatus.LOW);
    when(stockService.getLowStock(3)).thenReturn(List.of(lowStockItem));

    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/stock/low"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookCopyId").value(bookCopyId))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].stock").value(2))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].status").value("LOW"));
  }

  @Test
  void getLowStock_shouldReturn_OK_withCustomThreshold() throws Exception {
    StockResponseDTO item1 =
        new StockResponseDTO(bookCopyId, "The Great Gatsby", 2, StockStatus.LOW);
    StockResponseDTO item2 =
        new StockResponseDTO(UUID.randomUUID().toString(), "L'Étranger", 4, StockStatus.LOW);
    when(stockService.getLowStock(5)).thenReturn(List.of(item1, item2));

    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/stock/low?threshold=5"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
  }

  @Test
  void getLowStock_shouldReturn_EmptyList_whenNoLowStock() throws Exception {
    when(stockService.getLowStock(3)).thenReturn(List.of());

    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/stock/low"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0));
  }

  @Test
  void getLowStock_shouldReturn_KO_with_negativeStock() throws Exception {
    StockResponseDTO negative =
        new StockResponseDTO(bookCopyId, "Things fall apart", -3, StockStatus.OUT_OF_STOCK);

    when(stockService.getLowStock(3)).thenReturn(List.of(negative));

    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/stock/low"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].stock").value(-3))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].status").value("OUT_OF_STOCK"));
  }
}
