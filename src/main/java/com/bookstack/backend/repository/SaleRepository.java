package com.bookstack.backend.repository;

import com.bookstack.backend.enums.Status;
import com.bookstack.backend.repository.model.JSale;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SaleRepository extends JpaRepository<JSale, String> {
  List<JSale> findByCustomerId(String customerId);

  List<JSale> findBySaleDateBetween(Instant saleDate, Instant saleDate2);

  List<JSale> findByStatus(Status status);

  @Query(
      """
      SELECT SUM(ph.price) FROM JSale s
      JOIN s.saleItems si
            JOIN si.bookCopy bc
                  JOIN bc.priceHistories ph
      WHERE s.status = 'COMPLETED'
      AND s.saleDate BETWEEN :start AND :end
      """)
  BigDecimal getTotalRevenue(@Param("start") Instant start, @Param("end") Instant end);

  @Query(
          value = """
                    SELECT SUM(ph.price * si.quantity) AS total_revenue
                    FROM sale_item si
                    JOIN sale s ON si.sale_id = s.id
                    JOIN book_copy bc ON si.book_copy_id = bc.id
                    JOIN book b ON bc.book_id = b.id
                    JOIN book_genre bg ON b.id = bg.book_id
                    JOIN genre g ON bg.genre_id = g.id
                    CROSS JOIN LATERAL (
                        SELECT ph.price
                        FROM price_history ph
                        WHERE ph.book_copy_id = bc.id
                          AND ph.changed_at <= s.sale_date
                        ORDER BY ph.changed_at DESC
                        LIMIT 1
                    ) ph
                    WHERE s.status = 'COMPLETED'
                      AND g.name = :genre;   -- replace with actual genre name or use a parameter
                  """, nativeQuery = true
  )
  BigDecimal getTotalRevenueByGenreName(@Param("genre") String genre);
}
