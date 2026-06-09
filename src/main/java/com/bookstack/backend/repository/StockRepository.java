package com.bookstack.backend.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StockRepository extends JpaRepository<Stock, UUID> {
  Optional<Stock> findByBookCopyId(UUID bookCopyId);

  List<Stock> findByLibraryId(UUID libraryId);

  @Query(
      "SELECT s FROM Stock s WHERE s.library.libraryId = :libId " + "AND s.quantityAvailable > 0")
  List<Stock> findAvailableStockByLibrary(@Param("libId") UUID libId);
}
