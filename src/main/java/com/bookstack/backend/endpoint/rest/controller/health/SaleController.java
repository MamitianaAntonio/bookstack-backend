package com.bookstack.backend.endpoint.rest.controller.health;

import com.bookstack.backend.service.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/sale")
public class SaleController {
    private final SaleService saleService;

    @GetMapping("/revenue")
    public ResponseEntity<?> getTotalRevenueByGenre(@RequestParam String genre) {
        try {
            BigDecimal revenue = saleService.getTotalRevenueByGenre(genre);
            return ResponseEntity.ok(Map.of(
                    "genre", genre,
                    "totalRevenue", revenue
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
