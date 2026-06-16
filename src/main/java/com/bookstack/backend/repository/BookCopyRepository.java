package com.bookstack.backend.repository;

import com.bookstack.backend.entity.BookCopy;
import com.bookstack.backend.enums.Format;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookCopyRepository extends JpaRepository<BookCopy, String> {
  List<BookCopy> findByBookId(String bookId);

  List<BookCopy> findByFormat(Format format);

  List<BookCopy> findByPriceLessThanEqual(BigDecimal maxPrice);

  List<BookCopy> findByPriceGreaterThanEqual(BigDecimal minPrice);

  @Query("SELECT bc FROM BookCopy bc WHERE bc.price BETWEEN :min AND :max")
  List<BookCopy> findByPriceRange(@Param("min") BigDecimal min, @Param("max") BigDecimal max);
}
