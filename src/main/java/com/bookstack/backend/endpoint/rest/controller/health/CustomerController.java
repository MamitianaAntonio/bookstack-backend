package com.bookstack.backend.endpoint.rest.controller.health;

import com.bookstack.backend.dto.SaleRequest;
import com.bookstack.backend.model.Customer;
import com.bookstack.backend.model.Sale;
import com.bookstack.backend.repository.CustomerRepository;
import com.bookstack.backend.repository.SaleRepository;
import com.bookstack.backend.service.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private SaleService saleService;

    @PostMapping("{id}/sale")
    public ResponseEntity<?> createSale(@RequestBody SaleRequest saleRequest, @PathVariable String id) {
        try {
            Sale createdSale = saleService.create(id, saleRequest);
            return ResponseEntity.ok().body(createdSale);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
