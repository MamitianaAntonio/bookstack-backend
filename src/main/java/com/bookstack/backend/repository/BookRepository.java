package com.bookstack.backend.repository;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID>{
    List<Book> findByTitle(String title);
    List<Book> findByAuthorId(UUID authorId);
    Optional<Book> findByIsbn(String isbn);
    List<Book> findByPublishedAtAfter(LocalDate date);

    @Query("SELECT b FROM Book b JOIN b.genres g WHERE g.name = :genre")
    List<Book> findByGenre(@Param("genre") String genre);

    @Query("SELECT b FROM Book b WHERE b.title ILIKE %:keyword% OR b.summary ILIKE %:keyword%")
    List<Book> findByKeyword(@Param("keyword") String keyword);
}
