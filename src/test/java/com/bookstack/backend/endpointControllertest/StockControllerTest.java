package com.bookstack.backend.endpointControllertest;

import com.bookstack.backend.dto.FormatStockDTO;
import com.bookstack.backend.dto.response.BookStockResponseDTO;
import com.bookstack.backend.endpoint.rest.controller.health.BookController;
import com.bookstack.backend.enums.Format;
import com.bookstack.backend.enums.Language;
import com.bookstack.backend.exception.GlobalExceptionHandler;
import com.bookstack.backend.exception.NotFoundException;
import com.bookstack.backend.service.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@WebMvcTest({BookController.class, GlobalExceptionHandler.class})
public class StockControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StockService stockService;

    private BookStockResponseDTO bookStockResponseDTO;
    private FormatStockDTO formatStockDTO;
    private String bookId;
    private String bookCopyId;

    @BeforeEach
    void setup() {
        bookId = UUID.randomUUID().toString();
        bookCopyId = UUID.randomUUID().toString();

        FormatStockDTO format1 = new FormatStockDTO(
                bookCopyId,
                "The Great Gatsby",
                Format.PAPERBACK,
                Language.ENG,
                3
        );

        FormatStockDTO format2 = new FormatStockDTO(
                UUID.randomUUID().toString(),
                "The Great Gatsby",
                Format.HARDBACK,
                Language.ENG,
                12
        );

        bookStockResponseDTO = new BookStockResponseDTO(
                bookCopyId,
                "The Great Gatsby",
                "978-0743273565",
                15,
                List.of(format1, format2)

        );

        formatStockDTO = new FormatStockDTO(
                bookCopyId,
                "The Great Gatsby",
                Format.HARDBACK,
                Language.ENG,
                3
        );
    }

    @Test
    void getBookStock_shouldReturn_OK() throws Exception {
        when(stockService.getBookStock(bookId)).thenReturn(bookStockResponseDTO);

        mockMvc
                .perform(MockMvcRequestBuilders.get("api/stock/book/" + bookId))
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
        when(stockService.getBookStock(wrongId))
                .thenThrow(new NotFoundException("Book not found"));

        mockMvc
                .perform(MockMvcRequestBuilders.get("api/stock/book/" + wrongId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Book not found"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("404"));
    }


    @Test
    void getFormatStock_shouldReturn_OK() throws Exception {
        when(stockService.getFormatStock(bookCopyId)).thenReturn(formatStockDTO);

        mockMvc
                .perform(MockMvcRequestBuilders.get("api/stock/format/" + bookCopyId))
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
        when(stockService.getFormatStock(wrongId)).thenThrow(new NotFoundException("Book copy not found"));

        mockMvc
                .perform(MockMvcRequestBuilders.get("api/stock/format/" + wrongId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Book copy not found"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("404"));
    }



}
