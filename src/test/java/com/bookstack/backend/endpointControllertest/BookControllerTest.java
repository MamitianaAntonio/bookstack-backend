package com.bookstack.backend.endpointControllertest;

import static org.mockito.Mockito.when;

import com.bookstack.backend.dto.BookRequestDTO;
import com.bookstack.backend.endpoint.rest.controller.health.BookController;
import com.bookstack.backend.enums.Language;
import com.bookstack.backend.model.Author;
import com.bookstack.backend.model.Book;
import com.bookstack.backend.model.Genre;
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

@WebMvcTest({BookController.class})
public class BookControllerTest {
  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private BookService bookService;

  private Book lePetitPrince;
  private Book javaPourLesNuls;
  private Author dev;
  private Author antoine;
  private Genre fiction;
  private Genre education;
  private BookRequestDTO bookRequestDTO;

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
}
