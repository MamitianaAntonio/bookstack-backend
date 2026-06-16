package com.bookstack.backend.mapper;

import com.bookstack.backend.model.Author;
import com.bookstack.backend.repository.model.JAuthor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AuthorMapper {
    public List<Author> toModel(List<JAuthor> jAuthors) {
        return jAuthors.stream().map(this::toModel).toList();
    }

    public Author toModel(JAuthor jAuthor) {
        Author author = new Author();
        author.setId(jAuthor.getId());
        author.setFirstName(jAuthor.getFirstName());
        author.setLastName(jAuthor.getLastName());
        author.setBiography(jAuthor.getBiography());
        author.setLanguage(jAuthor.getLanguage());
        author.setEmail(jAuthor.getEmail());
        return author;
    }

    public List<JAuthor> toEntity(List<Author> authors) {
        return authors.stream().map(this::toEntity).toList();
    }

    public JAuthor toEntity(Author author) {
        JAuthor jAuthor = new JAuthor();
        jAuthor.setId(author.getId());
        jAuthor.setFirstName(author.getFirstName());
        jAuthor.setLastName(author.getLastName());
        jAuthor.setBiography(author.getBiography());
        jAuthor.setLanguage(author.getLanguage());
        jAuthor.setEmail(author.getEmail());
        return jAuthor;
    }
}
