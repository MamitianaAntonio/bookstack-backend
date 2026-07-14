package com.bookstack.backend.endpoint.rest.controller;

import com.bookstack.backend.dto.request.SaleRequest;
import com.bookstack.backend.exception.BadRequestException;
import com.bookstack.backend.exception.NotFoundException;
import com.bookstack.backend.model.Sale;
import com.bookstack.backend.service.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
  private SaleService saleService;

  @PostMapping("{id}/sale")
  public ResponseEntity<?> createSale(
      @RequestBody SaleRequest saleRequest, @PathVariable String id) {
    try {
      Sale createdSale = saleService.create(id, saleRequest);
      return ResponseEntity.ok().body(createdSale);
    } catch (BadRequestException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    } catch (NotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }
}
