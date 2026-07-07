package com.bookstack.backend.repository;

import com.bookstack.backend.enums.Format;
import com.bookstack.backend.enums.Language;
import com.bookstack.backend.repository.model.JBookCopy;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookCopyRepository extends JpaRepository<JBookCopy, String> {
  List<JBookCopy> findByBookId(String bookId);

  List<JBookCopy> findByFormat(Format format);

  List<JBookCopy> findByLanguage(Language language);

  List<JBookCopy> findByBookIdAndFormat(String bookId, Format format);

  @Query(
      "SELECT bc FROM JBookCopy bc JOIN bc.priceHistories ph WHERE ph.price BETWEEN :min AND :max")
  List<JBookCopy> findByPriceRange(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

  List<JBookCopy> findByPublisherId(String publisherId);

  @Query(
          """
                SELECT COALESCE(SUM(ai.quantity), 0) - COALESCE(SUM(si.quantity), 0) 
                FROM JBookCopy bc
                LEFT JOIN bc.arrivalItems ai 
                LEFT JOIN bc.saleItems si
                WHERE bc.id = :bookCopyId
                  """
  )
  Integer calculateTotalStockByBookCopyId(@Param("bookCopyId") String bookCopyId);

  @Query(
          """
            SELECT COALESCE(SUM(ai.quantity), 0) - COALESCE(SUM(si.quantity), 0) 
            FROM JBookCopy bc
            LEFT JOIN bc.arrivalItems ai 
            LEFT JOIN bc.saleItems si
            WHERE bc.book.id = :bookId 
          """
  )
  Integer calculateTotalStockByBookId(@Param("bookId") String bookId);

}
