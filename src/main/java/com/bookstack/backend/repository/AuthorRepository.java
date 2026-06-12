package com.bookstack.backend.repository;

import com.bookstack.backend.entity.Author;
import com.bookstack.backend.entity.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthorRepository extends JpaRepository<Author, String> {
  List<Author> findByNationality(String nationality);

  Optional<Author> findByEmail(String email);

  List<Author> findAuthorByBook(Book book);

  @Query("SELECT a from Author a WHERE a.nickname ILIKE %:name%")
  List<Author> findAuthorByName(@Param("name") String name);
}
