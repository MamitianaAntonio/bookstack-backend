package com.bookstack.backend.repository;

import java.util.List;
import java.util.Optional;

import com.bookstack.backend.repository.model.JGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GenreRepository extends JpaRepository<JGenre, String> {
  Optional<JGenre> findByName(String name);

  @Query("SELECT g FROM Genre g WHERE g.description ILIKE %:keyword%")
  List<JGenre> findByKeyword(@Param("keyword") String keyword);
}
