package com.bookstack.backend.endpoint.rest.controller.health;

import com.bookstack.backend.model.Genre;
import com.bookstack.backend.service.GenreService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.bookstack.backend.service.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class GenreController {
  private final GenreService service;
  private final SaleService saleService;

  @GetMapping("/genres")
  public List<Genre> getAll() {
    return service.findAll();
  }

  @GetMapping("/genres/{id}")
  public Genre getById(@PathVariable String id) {
    return service.findById(id);
  }

  @PostMapping("/genres")
  public List<Genre> create(@RequestBody List<Genre> toCreate) {
    return service.create(toCreate);
  }

  @PutMapping("/genres/{id}")
  public Genre update(@PathVariable String id, @RequestBody Genre toUpdate) {
    return service.update(id, toUpdate);
  }

  @DeleteMapping("/genres/{id}")
  public void delete(@PathVariable String id) {
    service.delete(id);
  }

  @GetMapping("/genre/revenue")
  public ResponseEntity<?> getTotalRevenueByGenre(@RequestParam String genre) {
    try {
      BigDecimal revenue = saleService.getTotalRevenueByGenre(genre);
      return ResponseEntity.ok(
              Map.of(
                      "genre", genre,
                      "totalRevenue", revenue));
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
    }
  }
}
