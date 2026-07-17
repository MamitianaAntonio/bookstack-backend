package com.bookstack.backend.endpoint.rest.controller.health;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.bookstack.backend.endpoint.rest.controller.GenreController;
import com.bookstack.backend.service.SaleService;
import java.math.BigDecimal;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class GenreControllerTest {
  @Mock private SaleService saleService;

  @InjectMocks private GenreController genreController;

  @Test
  void getTotalRevenueByGenre_shouldReturn200_whenValid_Genre() {
    when(saleService.getTotalRevenueByGenre("Fiction")).thenReturn(new BigDecimal("450000"));

    ResponseEntity<?> response = genreController.getTotalRevenueByGenre("Fiction");

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
  }

  @Test
  void getTotalRevenueByGenre_shouldReturnCorrectBody() {
    when(saleService.getTotalRevenueByGenre("Fiction")).thenReturn(new BigDecimal("450000"));

    ResponseEntity<?> response = genreController.getTotalRevenueByGenre("Fiction");

    Map<?, ?> body = (Map<?, ?>) response.getBody();
    assertNotNull(body);
    assertEquals("Fiction", body.get("genre"));
    assertEquals(new BigDecimal("450000"), body.get("totalRevenue"));
  }

  @Test
  void getTotalRevenueByGenre_shouldReturn500_whenExceptionIsThrown() {
    when(saleService.getTotalRevenueByGenre("Fiction")).thenThrow(new RuntimeException("DB error"));

    ResponseEntity<?> response = genreController.getTotalRevenueByGenre("Fiction");

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
  }

  @Test
  void getTotalRevenueByGenre_shouldReturn200_whenNoSales() {
    when(saleService.getTotalRevenueByGenre("Unknown")).thenReturn(BigDecimal.ZERO);

    ResponseEntity<?> response = genreController.getTotalRevenueByGenre("Unknown");

    assertEquals(HttpStatus.OK, response.getStatusCode());
    Map<?, ?> body = (Map<?, ?>) response.getBody();
    assertNotNull(body);
    assertEquals(BigDecimal.ZERO, body.get("totalRevenue"));
  }
}
