package com.bookstack.backend.repository;

import com.bookstack.backend.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GenreRepository extends JpaRepository<Genre, UUID> {
    Optional<Genre> findByName(String name);

    @Query("SELECT g FROM Genre g WHERE g.description ILIKE %:keyword%")
    List<Genre> findByKeyword(@Param("keyword") String keyword);
}
