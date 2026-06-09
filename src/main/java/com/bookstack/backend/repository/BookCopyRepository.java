package com.bookstack.backend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.UUID;

public interface BookCopyRepository extends JpaRepository<BookCopy, UUID>{
    List<BookCopy> findByBookId(UUID bookId);
    List<BookCopy> findByFormat(BookCopyFormat format);
    List<BookCopy> findByPriceLessThanEqual(BigDecimal maxPrice);

    @Query("SELECT bc FROM BookCopy bc WHERE bc.price BETWEEN :min AND :max")
    List<BookCopy> findByPriceRange(@Param("min") BigDecimal min, @Param("max") BigDecimal max);
}
