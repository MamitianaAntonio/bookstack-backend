package com.bookstack.backend.mapper;

import com.bookstack.backend.model.Sale;
import com.bookstack.backend.repository.model.JSale;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SaleMapper {
  private SaleItemMapper saleItemMapper;
  private CustomerMapper customerMapper;

  public Sale toModel(JSale entity) {
    return Sale.builder()
        .id(entity.getId())
        .saleDate(entity.getSaleDate())
        .paymentMethod(entity.getPaymentMethod())
        .status(entity.getStatus())
        .customer(customerMapper.toModel(entity.getCustomer()))
        .saleItems(saleItemMapper.toModel(entity.getSaleItems()))
        .build();
  }

  public List<Sale> toModel(List<JSale> entities) {
    return entities.stream().map(this::toModel).toList();
  }

  public JSale toEntity(Sale model) {
    return JSale.builder()
        .id(model.getId())
        .saleDate(model.getSaleDate())
        .paymentMethod(model.getPaymentMethod())
        .status(model.getStatus())
        .customer(customerMapper.toEntity(model.getCustomer()))
        .saleItems(saleItemMapper.toEntity(model.getSaleItems()))
        .build();
  }

  public List<JSale> toEntity(List<Sale> models) {
    return models.stream().map(this::toEntity).toList();
  }
}
