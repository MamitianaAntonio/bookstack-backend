package com.bookstack.backend.repository;

import com.bookstack.backend.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface LibraryRepository extends JpaRepository<Library, UUID> {
    List<Library> findByName(String name);

    List<Library> findByLocation(String location);

    List<Library> findByLocationAndName(String location, String name);

    List<Library> findByOpeningTimeAndClosingTime(LocalTime openingTime, LocalTime closingTime);
}

