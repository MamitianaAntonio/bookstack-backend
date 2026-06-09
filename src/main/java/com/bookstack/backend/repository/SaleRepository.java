package com.bookstack.backend.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SaleRepository extends JpaRepository<Sale, UUID> {
  List<Sale> findByCustomer_CustomerId(UUID customerId);

  List<Sale> findBySaleDateBetween(LocalDateTime start, LocalDateTime end);

  List<Sale> findByStatus(SaleStatus status);

  @Query(
      "SELECT SUM(s.totalAmount) FROM Sale s WHERE s.status = 'COMPLETED' "
          + "AND s.saleDate BETWEEN :start AND :end")
  BigDecimal getTotalRevenue(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
