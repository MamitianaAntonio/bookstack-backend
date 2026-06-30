package com.bookstack.backend.repository;

import com.bookstack.backend.repository.model.JSaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SaleItemRepository extends JpaRepository<JSaleItem, String> {
  @Query(
      "SELECT COALESCE(SUM(si.quantity), 0) FROM JSaleItem si WHERE si.bookCopy.id = :bookCopyId")
  Integer sumQuantityByBookCopyId(@Param("bookCopyId") String bookCopyId);
}
