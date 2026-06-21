package com.bookstack.backend.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequestDTO {
  private String title;
  private String summary;
  private String isbn;
  private List<String> authorIds;
  private List<String> genreIds;
}
