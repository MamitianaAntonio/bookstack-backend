package com.bookstack.backend.repository;

import java.util.List;
import java.util.Optional;

import com.bookstack.backend.repository.model.JBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<JBook, String> {
  List<JBook> findByTitle(String title);

  Optional<JBook> findByIsbn(String isbn);

  @Query("SELECT b FROM Book b JOIN b.authors a WHERE a.id = :authorId")
  List<JBook> findByAuthorId(@Param("authorId") String authorId);

  @Query("SELECT b FROM Book b JOIN b.genres g WHERE g.name = :genre")
  List<JBook> findByGenre(@Param("genre") String genre);

  @Query("SELECT b FROM Book b WHERE b.title ILIKE %:keyword% OR b.summary ILIKE %:keyword%")
  List<JBook> findByKeyword(@Param("keyword") String keyword);

  @Query(
      """
                  SELECT b FROM Book b
                  LEFT JOIN b.authors a
                  LEFT JOIN b.genres g
                  WHERE (:authorId IS NULL OR a.id = :authorId)
                  AND (:genreId IS NULL OR g.id = :genreId)
                  AND (:title IS NULL OR b.title ILIKE %:title%)
      """)
  List<JBook> findByCriteria(
      @Param("authorId") String authorId,
      @Param("genreId") String genreId,
      @Param("title") String title);
}
