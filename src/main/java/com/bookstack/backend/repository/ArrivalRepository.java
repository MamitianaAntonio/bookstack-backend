package com.bookstack.backend.repository;

import com.bookstack.backend.entity.Arrival;
import com.bookstack.backend.enums.Condition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface ArrivalRepository extends JpaRepository<Arrival, String> {
    List<Arrival> findByArrivalDateBetween(Timestamp start, Timestamp end);

    List<Arrival> findByCondition(Condition condition);
}
