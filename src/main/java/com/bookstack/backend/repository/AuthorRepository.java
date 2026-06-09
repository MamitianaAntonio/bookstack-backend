package com.bookstack.backend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
    List<Author> findByNationality(String nationality);
    Optional<Author> findByEmail(String email);
    List<Author> findByLastName(String name);

    @Query("SELECT a FROM Author a WHERE a.authorName LIKE %:name%")
    List<Author> findByAuthorName(@Param("name") String name);
}
