package com.bookstack.backend.repository;

import com.bookstack.backend.entity.Book;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, String> {
  List<Book> findByTitle(String title);

  List<Book> findByAuthorId(String authorId);

  Optional<Book> findByIsbn(String isbn);
  
  @Query("SELECT b FROM Book b JOIN b.genres g WHERE g.name = :genre")
  List<Book> findByGenre(@Param("genre") String genre);

  @Query("SELECT b FROM Book b WHERE b.title ILIKE %:keyword% OR b.summary ILIKE %:keyword%")
  List<Book> findByKeyword(@Param("keyword") String keyword);

  @Query(
      """
                  SELECT b FROM Book b
                  LEFT JOIN b.authors a
                  LEFT JOIN b.genres g
                  WHERE (:authorId IS NULL OR a.id = :authorId)
                  AND (:genreId IS NULL OR g.id = :genreId)
                  AND (:title IS NULL OR b.title ILIKE %:title%)
      """)
  List<Book> findByCriteria(
      @Param("authorId") String authorId,
      @Param("genreId") String genreId,
      @Param("title") String title);
}
