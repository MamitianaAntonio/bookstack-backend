package com.bookstack.backend.mapper;

import com.bookstack.backend.model.SaleItem;
import com.bookstack.backend.repository.model.JSaleItem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SaleItemMapper {
    private BookCopyMapper bookCopyMapper;

    public SaleItem toModel(JSaleItem entity) {
        return SaleItem.builder()
                .id(entity.getId())
                .bookCopy(bookCopyMapper.toModel(entity.getBookCopy()))
                .quantity(entity.getQuantity())
                .build();
    }

    public List<SaleItem> toModel(List<JSaleItem> entities) {
        return entities.stream().map(this::toModel).collect(Collectors.toList());
    }

    public JSaleItem toEntity(SaleItem model) {
        return JSaleItem.builder()
                .id(model.getId())
                .quantity(model.getQuantity())
                .bookCopy(bookCopyMapper.toEntity(model.getBookCopy()))
                .build();
    }

    public List<JSaleItem> toEntity(List<SaleItem> models) {
        return models.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
