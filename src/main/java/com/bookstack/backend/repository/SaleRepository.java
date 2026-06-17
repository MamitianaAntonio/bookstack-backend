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
}
