package com.bookstack.backend.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.bookstack.backend.dto.BookCopyRequest;
import com.bookstack.backend.dto.SaleItemRequest;
import com.bookstack.backend.dto.SaleRequest;
import com.bookstack.backend.exception.BadRequestException;
import com.bookstack.backend.exception.NotFoundException;
import com.bookstack.backend.mapper.SaleMapper;
import com.bookstack.backend.model.Sale;
import com.bookstack.backend.repository.BookCopyRepository;
import com.bookstack.backend.repository.CustomerRepository;
import com.bookstack.backend.repository.SaleRepository;
import com.bookstack.backend.repository.model.JBookCopy;
import com.bookstack.backend.repository.model.JCustomer;
import com.bookstack.backend.repository.model.JSale;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SaleServiceTest {

  @Mock private SaleRepository saleRepository;
  @Mock private CustomerRepository customerRepository;
  @Mock private BookCopyRepository bookCopyRepository;
  @Mock private SaleMapper saleMapper;

  @InjectMocks private SaleService saleService;

  private String customerId;
  private JCustomer mockCustomer;
  private JBookCopy mockBookCopy;
  private SaleRequest validRequest;
  private JSale mockSaleToSave;
  private JSale mockSavedSale;
  private Sale mockSaleModel;

  @BeforeEach
  void setUp() {
    customerId = "cust123";
    mockCustomer = new JCustomer();
    mockCustomer.setId(customerId);

    String bookCopyId = "book456";
    mockBookCopy = new JBookCopy();
    mockBookCopy.setId(bookCopyId);

    SaleItemRequest itemRequest = new SaleItemRequest();

    BookCopyRequest bookCopyRequest = new BookCopyRequest();
    bookCopyRequest.setBookId(bookCopyId);
    itemRequest.setBookCopy(bookCopyRequest);
    itemRequest.setQuantity(2);

    validRequest = new SaleRequest();
    validRequest.setItems(List.of(itemRequest));

    mockSaleToSave = new JSale();
    mockSavedSale = new JSale();
    mockSavedSale.setId(String.valueOf(1L));

    mockSaleModel = new Sale();
  }

  @Test
  void create_whenValidRequest_shouldSaveAndReturnSale() {
    when(customerRepository.findById(customerId)).thenReturn(Optional.of(mockCustomer));
    when(bookCopyRepository.findById(anyString())).thenReturn(Optional.of(mockBookCopy));
    when(saleRepository.save(any(JSale.class))).thenReturn(mockSavedSale);
    when(saleMapper.toModel(mockSavedSale)).thenReturn(mockSaleModel);

    Sale result = saleService.create(customerId, validRequest);

    assertNotNull(result);
    assertEquals(mockSaleModel, result);

    verify(saleRepository, times(1)).save(any(JSale.class));
    verify(customerRepository, times(1)).findById(customerId);
    verify(bookCopyRepository, times(1)).findById(anyString());
  }

  @Test
  void create_whenCustomerNotFound_shouldThrowException() {
    when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
    assertThrows(NotFoundException.class, () -> saleService.create(customerId, validRequest));
    verify(saleRepository, never()).save(any());
  }

  @Test
  void create_whenItemsEmpty_shouldThrowBadRequestException() {
    SaleRequest emptyRequest = new SaleRequest();
    emptyRequest.setItems(List.of());

    BadRequestException exception =
        assertThrows(BadRequestException.class, () -> saleService.create(customerId, emptyRequest));
    assertEquals("SaleRequest must have at least one item!", exception.getMessage());

    verify(customerRepository, never()).findById(anyString());
    verify(saleRepository, never()).save(any());
  }

  @Test
  void create_whenBookCopyEmpty_shouldThrowBadRequestException() {
    when(customerRepository.findById(customerId)).thenReturn(Optional.of(mockCustomer));
    when(bookCopyRepository.findById(anyString())).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> saleService.create(customerId, validRequest));
    verify(saleRepository, never()).save(any());
  }

  @Test
  void getTotalRevenueByGenre_shouldReturnRevenue_whenSalesExist() {
    when(saleRepository.getTotalRevenueByGenreName("Fiction")).thenReturn(new BigDecimal("450000"));

    BigDecimal result = saleService.getTotalRevenueByGenre("Fiction");

    assertEquals(new BigDecimal("450000"), result);
  }

  @Test
  void getTotalRevenueByGenre_shouldReturnZero_whenNoSalesIsFound() {
    when(saleRepository.getTotalRevenueByGenreName("Unknown")).thenReturn(null);

    BigDecimal result = saleService.getTotalRevenueByGenre("Unknown");

    assertEquals(BigDecimal.ZERO, result);
  }

  @Test
  void getTotalRevenueByGenre_shouldCallRepository_withCorrectGenre() {
    when(saleRepository.getTotalRevenueByGenreName("Fiction")).thenReturn(BigDecimal.ZERO);

    BigDecimal result = saleService.getTotalRevenueByGenre("Fiction");

    verify(saleRepository, times(1)).getTotalRevenueByGenreName("Fiction");
  }
}
