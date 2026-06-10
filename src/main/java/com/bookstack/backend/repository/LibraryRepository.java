package com.bookstack.backend.repository;

import com.bookstack.backend.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;

public interface LibraryRepository extends JpaRepository<Library, String> {
    List<Library> findByName(String name);

    List<Library> findByLocation(String location);

    List<Library> findByLocationAndName(String location, String name);

    List<Library> findByOpeningTimeAndClosingTime(LocalTime openingTime, LocalTime closingTime);
}

