package com.bookstack.backend.repository;

import java.util.UUID;

public interface GenreRepository extends JpaRepository<Genre, UUID>{
    Optional<Genre> findByName(String name);
}
