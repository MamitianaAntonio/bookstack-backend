package com.bookstack.backend.mapper;

import com.bookstack.backend.model.Book;
import com.bookstack.backend.repository.model.JBook;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class BookMapper {
    public final GenreMapper genreMapper;
    public final AuthorMapper authorMapper;

    public List<Book> toModel(List<JBook> jBooks) {
        return jBooks.stream().map(this::toModel).toList();
    }

    public Book toModel(JBook jBook) {
        Book book = new Book();
        book.setId(jBook.getId());
        book.setTitle(jBook.getTitle());
        book.setSummary(jBook.getSummary());
        book.setIsbn(jBook.getIsbn());
        book.setGenres(genreMapper.toModel(jBook.getGenres()));
        book.setAuthors(authorMapper.toModel(jBook.getAuthors()));
        return book;
    }

    public List<JBook> toEntity(List<Book> books) {
        return books.stream().map(this::toEntity).toList();
    }

    public JBook toEntity(Book book) {
        JBook jBook = new JBook();
        jBook.setId(book.getId());
        jBook.setTitle(book.getTitle());
        jBook.setSummary(book.getSummary());
        jBook.setIsbn(book.getIsbn());
        jBook.setGenres(genreMapper.toEntity(book.getGenres()));
        jBook.setAuthors(authorMapper.toEntity(book.getAuthors()));
        return jBook;
    }
}
