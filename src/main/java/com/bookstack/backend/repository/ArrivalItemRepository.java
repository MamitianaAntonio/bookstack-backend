package com.bookstack.backend.repository;

import com.bookstack.backend.repository.model.JArrivalItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArrivalItemRepository extends JpaRepository<JArrivalItem, String> {
  @Query(
      "SELECT COALESCE(SUM(ai.quantity), 0) FROM JArrivalItem ai WHERE ai.bookCopy.id ="
          + " :bookCopyId")
  Integer sumQuantityByBookCopyId(@Param("bookCopyId") String bookCopyId);
}
