package com.bookstack.backend.repository;

import java.util.List;
import java.util.Optional;

import com.bookstack.backend.repository.model.JAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthorRepository extends JpaRepository<JAuthor, String> {
  List<JAuthor> findByNationality(String nationality);

  Optional<JAuthor> findByEmail(String email);

  @Query("SELECT a FROM JAuthor a JOIN a.books b WHERE b.id = :bookId")
  List<JAuthor> findAuthorByBook(@Param("bookId") String bookId);

  @Query("SELECT a from JAuthor a WHERE a.firstName ILIKE %:name%")
  List<JAuthor> findAuthorByName(@Param("name") String name);
}
