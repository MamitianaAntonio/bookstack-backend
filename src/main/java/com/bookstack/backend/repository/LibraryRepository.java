package com.bookstack.backend.repository;

import java.util.UUID;

public interface LibraryRepository extends JpaRepository<Library, UUID> {
  List<Library> findByName(String name);
}
