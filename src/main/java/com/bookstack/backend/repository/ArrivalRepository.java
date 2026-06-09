package com.bookstack.backend.repository;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ArrivalRepository extends JpaRepository<Arrival, UUID> {
  List<Arrival> findByDateBetween(LocalDateTime start, LocalDateTime end);

  List<Arrival> findByCondition(ArrivalCondition condition);
}
