package com.bookstack.backend.repository;

import com.bookstack.backend.enums.Format;
import java.math.BigDecimal;
import java.util.List;

import com.bookstack.backend.repository.model.JBookCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookCopyRepository extends JpaRepository<JBookCopy, String> {
  List<JBookCopy> findByBookId(String bookId);

  List<JBookCopy> findByFormat(Format format);

  List<JBookCopy> findByPriceLessThanEqual(BigDecimal maxPrice);

  List<JBookCopy> findByPriceGreaterThanEqual(BigDecimal minPrice);

  @Query("SELECT bc FROM JBookCopy bc WHERE bc.price BETWEEN :min AND :max")
  List<JBookCopy> findByPriceRange(@Param("min") BigDecimal min, @Param("max") BigDecimal max);
}
