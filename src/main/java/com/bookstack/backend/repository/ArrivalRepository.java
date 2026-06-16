package com.bookstack.backend.repository;

import com.bookstack.backend.repository.model.JArrival;
import com.bookstack.backend.enums.Condition;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArrivalRepository extends JpaRepository<JArrival, String> {
  List<JArrival> findByArrivalDateBetween(Timestamp start, Timestamp end);

  List<JArrival> findByCondition(Condition condition);
}
