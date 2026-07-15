package com.bookstack.backend.endpointControllertest;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.bookstack.backend.dto.BookInfoDTO;
import com.bookstack.backend.dto.request.BookRequestDTO;
import com.bookstack.backend.endpoint.rest.controller.BookController;
import com.bookstack.backend.enums.Language;
import com.bookstack.backend.exception.GlobalExceptionHandler;
import com.bookstack.backend.exception.NotFoundException;
import com.bookstack.backend.model.Author;
import com.bookstack.backend.model.Book;
import com.bookstack.backend.model.Genre;
import com.bookstack.backend.service.BookExternalService;
import com.bookstack.backend.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest({BookController.class, GlobalExceptionHandler.class})
public class BookControllerTest {
  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private BookService bookService;

  @MockBean private BookExternalService bookExternalService;

  private Book lePetitPrince;
  private Book javaPourLesNuls;
  private Author dev;
  private Author antoine;
  private Genre fiction;
  private Genre education;
  private BookRequestDTO bookRequestDTO;
  private BookRequestDTO updateDTO;
  private BookInfoDTO bookInfoDTO;

  @BeforeEach
  void setUp() {
    antoine =
        Author.builder()
            .id(UUID.randomUUID().toString())
            .firstName("Antoine")
            .lastName("de Saint-Exupéry")
            .biography("French aviator and writer")
            .language(Language.FR)
            .email("antoine@example.com")
            .books(List.of())
            .build();

    fiction =
        Genre.builder()
            .id(UUID.randomUUID().toString())
            .name("Fiction")
            .description("Literary fiction and storytelling")
            .build();

    lePetitPrince =
        Book.builder()
            .id(UUID.randomUUID().toString())
            .title("Le Petit Prince")
            .summary("A poetic story about friendship, love, and life lessons")
            .isbn("978-0156012195")
            .authors(List.of(antoine))
            .genres(List.of(fiction))
            .build();

    dev =
        Author.builder()
            .id(UUID.randomUUID().toString())
            .firstName("Java")
            .lastName("Developer")
            .biography("Software engineer writing Java tutorials")
            .language(Language.ENG)
            .email("java.dev@example.com")
            .books(List.of())
            .build();

    education =
        Genre.builder()
            .id(UUID.randomUUID().toString())
            .name("Education")
            .description("Programming and learning content")
            .build();

    javaPourLesNuls =
        Book.builder()
            .id(UUID.randomUUID().toString())
            .title("Java pour les nuls")
            .summary("A beginner-friendly guide to Java programming")
            .isbn("978-1234567890")
            .authors(List.of(dev))
            .genres(List.of(education))
            .build();

    bookRequestDTO =
        BookRequestDTO.builder()
            .title("Le Petit Prince")
            .summary("A poetic story about friendship, love and life lessons")
            .isbn("978-0156012195")
            .authorIds(List.of(antoine.getId()))
            .genreIds(List.of(fiction.getId()))
            .build();

    updateDTO =
        BookRequestDTO.builder()
            .title("Le Petit Prince Updated")
            .summary("Updated summary")
            .isbn("978-0156012195")
            .authorIds(List.of(antoine.getId()))
            .genreIds(List.of(fiction.getId()))
            .build();

    bookInfoDTO =
        BookInfoDTO.builder()
            .title("Le Petit Prince")
            .authors("Antoine de Saint-Exupéry")
            .isbn("978-0156012195")
            .source("OPEN_LIBRARY")
            .description("A poetic story about friendship, love and life lessons")
            .pageCount(93)
            .publishDate("6 Avril 1943")
            .imageUrl("https://covers.openlibrary.org/b/id/7268667-L.jpg")
            .openLibraryUrl("https://openlibrary.org/books/OL25435833M/The_Little_Prince")
            .build();
  }

  @Test
  void getAllbooks_shouldReturn_ok() throws Exception {
    when(bookService.findAll()).thenReturn(List.of(lePetitPrince, javaPourLesNuls));

    mockMvc
        .perform(MockMvcRequestBuilders.get("/books"))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  void getBookById_shouldReturnBook() throws Exception {
    when(bookService.findById(lePetitPrince.getId())).thenReturn(lePetitPrince);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/books/" + lePetitPrince.getId()))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Le Petit Prince"));
  }

  @Test
  void createBooks_shouldReturn_201() throws Exception {
    when(bookService.create(ArgumentMatchers.any())).thenReturn(List.of(lePetitPrince));

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(List.of(bookRequestDTO))))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Le Petit Prince"));
  }

  @Test
  void updateBook_shouldReturn_200() throws Exception {
    when(bookService.update(ArgumentMatchers.eq(lePetitPrince.getId()), ArgumentMatchers.any()))
        .thenReturn(lePetitPrince);

    mockMvc
        .perform(
            MockMvcRequestBuilders.put("/books/" + lePetitPrince.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDTO)))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Le Petit Prince"));
  }

  @Test
  void updateBook_shouldReturn_404_whenBookNotFound() throws Exception {

    when(bookService.update(ArgumentMatchers.eq("wrong-id"), ArgumentMatchers.any()))
        .thenThrow(new NotFoundException("Book with id wrong-id not found"));

    mockMvc
        .perform(
            MockMvcRequestBuilders.put("/books/wrong-id")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDTO)))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  void deleteBook_shouldReturn_204() throws Exception {

    doNothing().when(bookService).delete(lePetitPrince.getId());

    mockMvc
        .perform(MockMvcRequestBuilders.delete("/books/" + lePetitPrince.getId()))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  @Test
  void searchBook_shouldReturn_ok() throws Exception {
    when(bookExternalService.getBookInfoDTO(lePetitPrince.getIsbn())).thenReturn(bookInfoDTO);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/books/search").param("isbn", "9780156012195"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  void searchBook_shouldReturn_404_whenBookNotFound() throws Exception {
    when(bookExternalService.getBookInfoDTO("9999023981999"))
        .thenThrow(new NotFoundException("Book not found"));

    mockMvc
        .perform(MockMvcRequestBuilders.get("/books/search").param("isbn", "9999023981999"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }
}
