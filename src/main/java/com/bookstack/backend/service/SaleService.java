package com.bookstack.backend.service;

import com.bookstack.backend.dto.SaleItemRequest;
import com.bookstack.backend.dto.SaleRequest;
import com.bookstack.backend.exception.BadRequestException;
import com.bookstack.backend.exception.NotFoundException;
import com.bookstack.backend.mapper.SaleMapper;
import com.bookstack.backend.model.Sale;
import com.bookstack.backend.repository.BookCopyRepository;
import com.bookstack.backend.repository.CustomerRepository;
import com.bookstack.backend.repository.SaleItemRepository;
import com.bookstack.backend.repository.SaleRepository;
import com.bookstack.backend.repository.model.JBookCopy;
import com.bookstack.backend.repository.model.JCustomer;
import com.bookstack.backend.repository.model.JSale;
import com.bookstack.backend.repository.model.JSaleItem;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SaleService {
  private final SaleRepository saleRepository;
  private final SaleItemRepository saleItemRepository;
  private final BookCopyRepository bookCopyRepository;
  private final CustomerRepository customerRepository;
  private final SaleMapper mapper;

  public List<Sale> findAll() {
    return mapper.toModel(saleRepository.findAll());
  }

  public Sale findById(String id) {
    return mapper.toModel(
        saleRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Sale with id: " + id + " not found!")));
  }

  @Transactional
  public Sale create(String customerId, SaleRequest saleRequest) {
    if (saleRequest.getItems() == null || saleRequest.getItems().isEmpty()) {
      throw new BadRequestException("SaleRequest must have at least one item!");
    }

    JCustomer customer =
        customerRepository
            .findById(customerId)
            .orElseThrow(
                () -> new NotFoundException("Customer with id: " + customerId + " not found!"));
    JSale jsale = new JSale();
    jsale.setSaleDate(saleRequest.getSaleDate());
    jsale.setPaymentMethod(saleRequest.getPaymentMethod());
    jsale.setStatus(saleRequest.getStatus());
    jsale.setCustomer(customer);

    List<JSaleItem> items = new ArrayList<>();
    for (SaleItemRequest itemRequest : saleRequest.getItems()) {
      if (itemRequest.getBookCopy() == null) {
        throw new BadRequestException("SaleRequest must have at least one book copy!");
      }
      String bookCopyId = itemRequest.getBookCopy().getBookId();
      JBookCopy jBookCopy =
          bookCopyRepository
              .findById(bookCopyId)
              .orElseThrow(
                  () -> new NotFoundException("Book copy with id: " + bookCopyId + " not found!"));
      JSaleItem jsaleItem = new JSaleItem();
      jsaleItem.setBookCopy(jBookCopy);
      jsaleItem.setQuantity(itemRequest.getQuantity());
      jsaleItem.setSale(jsale);
      items.add(jsaleItem);
    }
    JSale savedSale = saleRepository.save(jsale);
    return mapper.toModel(savedSale);
  }

  public BigDecimal getTotalRevenueByGenre(String genre) {
    BigDecimal revenue = saleRepository.getTotalRevenueByGenreName(genre);
    return revenue != null ? revenue : BigDecimal.ZERO;
  }
}
