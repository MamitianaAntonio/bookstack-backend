package com.bookstack.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookRequestDTO {
    private String title;
    private String summary;
    private String isbn;
    private List<String> authorIds;
    private List<String> genreIds;
}
