package com.bookstack.backend.mapper;

import com.bookstack.backend.model.Customer;
import com.bookstack.backend.repository.model.JCustomer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CustomerMapper {
    private SaleMapper saleMapper;

    public Customer toModel(JCustomer entity) {
        return Customer.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .password(entity.getPassword())
                .address(entity.getAddress())
                .build();
    }

    public List<Customer> toModel(List<JCustomer> entities) {
        return entities.stream()
                .map(this::toModel)
                .toList();
    }

    public JCustomer toEntity(Customer model) {
        return JCustomer.builder()
                .id(model.getId())
                .firstName(model.getFirstName())
                .lastName(model.getLastName())
                .phoneNumber(model.getPhoneNumber())
                .email(model.getEmail())
                .password(model.getPassword())
                .address(model.getAddress())
                .build();
    }

    public List<JCustomer> toEntity(List<Customer> models) {
        return models.stream()
                .map(this::toEntity)
                .toList();
    }
}
