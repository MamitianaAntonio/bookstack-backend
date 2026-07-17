package com.bookstack.backend.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookInfoDTO {
  private String title;
  private String authors;
  private String isbn;
  private String source;
  private String description;
  private String publishDate;
  private Integer pageCount;
  private String imageUrl;
  private String openLibraryUrl;
}
