package com.bookstack.backend.service;

import com.bookstack.backend.exception.NotFoundException;
import com.bookstack.backend.mapper.SaleMapper;
import com.bookstack.backend.model.Sale;
import com.bookstack.backend.repository.BookCopyRepository;
import com.bookstack.backend.repository.SaleItemRepository;
import com.bookstack.backend.repository.SaleRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;
    private final SaleItemRepository saleItemRepository;
    private final BookCopyRepository bookCopyRepository;
    private final SaleMapper mapper;

    public List<Sale> findAll() {
        return mapper.toModel(saleRepository.findAll());
    }

    public Sale findById(String id) {
        return mapper.toModel(
                saleRepository
                        .findById(id)
                        .orElseThrow(() -> new NotFoundException("Sale with id: " + id + " not found!"))
        );
    }

    @Transactional
    public List<Sale> create(List)
}
