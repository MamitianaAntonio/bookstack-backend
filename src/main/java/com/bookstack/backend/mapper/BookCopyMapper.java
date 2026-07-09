package com.bookstack.backend.mapper;

import com.bookstack.backend.model.BookCopy;
import com.bookstack.backend.repository.model.JBookCopy;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookCopyMapper {
  private final BookMapper bookMapper;
  private final PublisherMapper publisherMapper;

  public List<BookCopy> toModel(List<JBookCopy> bookCopies) {
    return bookCopies.stream().map(this::toModel).toList();
  }

  public BookCopy toModel(JBookCopy jBookCopy) {
    BookCopy bookCopy = new BookCopy();
    bookCopy.setId(jBookCopy.getId());
    bookCopy.setFormat(jBookCopy.getFormat());
    bookCopy.setLanguage(jBookCopy.getLanguage());
    if (jBookCopy.getBook() != null) {
      bookCopy.setBook(bookMapper.toModel(jBookCopy.getBook()));
    }
    if (jBookCopy.getPublisher() != null) {
      bookCopy.setPublisher(publisherMapper.toModel(jBookCopy.getPublisher()));
    }

    return bookCopy;
  }

  public List<JBookCopy> toEntity(List<BookCopy> bookCopies) {
    return bookCopies.stream().map(this::toEntity).toList();
  }

  public JBookCopy toEntity(BookCopy bookCopy) {
    JBookCopy bookCopyEntity = new JBookCopy();
    bookCopyEntity.setId(bookCopy.getId());
    bookCopyEntity.setFormat(bookCopy.getFormat());
    bookCopyEntity.setLanguage(bookCopy.getLanguage());
    if (bookCopy.getBook() != null) {
      bookCopyEntity.setBook(bookMapper.toEntity(bookCopy.getBook()));
    }
    if (bookCopy.getPublisher() != null) {
      bookCopyEntity.setPublisher(publisherMapper.toEntity(bookCopy.getPublisher()));
    }
    return bookCopyEntity;
  }
}
