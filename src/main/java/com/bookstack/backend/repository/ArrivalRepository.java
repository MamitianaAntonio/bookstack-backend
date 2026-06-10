package com.bookstack.backend.repository;

import com.bookstack.backend.entity.Arrival;
import com.bookstack.backend.enums.Condition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface ArrivalRepository extends JpaRepository<Arrival, UUID> {
    List<Arrival> findByArrivalDateBetween(Timestamp start, Timestamp end);

    List<Arrival> findByCondition(Condition condition);
}
