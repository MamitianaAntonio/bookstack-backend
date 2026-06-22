package com.bookstack.backend.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDTO {
  private String title;
  private String summary;
  private String isbn;
  private List<String> authorIds;
  private List<String> genreIds;
}
