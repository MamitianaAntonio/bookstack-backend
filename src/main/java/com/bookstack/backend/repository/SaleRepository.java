package com.bookstack.backend.repository;

import com.bookstack.backend.entity.Sale;
import com.bookstack.backend.enums.Status;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SaleRepository extends JpaRepository<Sale, String> {
  List<Sale> findByCustomerId(String customerId);

  List<Sale> findBySaleDateBetween(Timestamp start, Timestamp end);

  List<Sale> findByStatus(Status status);

  @Query(
      """
      SELECT SUM(bc.price) FROM Sale s JOIN s.
      bookCopies bc
      WHERE s.status ='COMPLETED'
      AND s.
      saleDate BETWEEN :
      start AND :end""")
  BigDecimal getTotalRevenue(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
