package com.bookstack.backend.repository;

import com.bookstack.backend.enums.Condition;
import com.bookstack.backend.repository.model.JArrival;
import java.time.Instant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArrivalRepository extends JpaRepository<JArrival, String> {
  List<JArrival> findByArrivalTimeBetween(Instant arrivalTime, Instant arrivalTime2);

  List<JArrival> findByCondition(Condition condition);
}
