package com.bookstack.backend.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.Map;
import lombok.*;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenLibraryResponse {
  private Map<String, BookData> books;

  @Data
  @NoArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class BookData {
    private String title;
    private List<AuthorData> authors;
    private String description;
    private Integer numberOfPages;
    private String publishDate;
    private CoverData cover;
    private String url;
    private String key;
    private List<PublisherData> publishers;
  }

  @Data
  @NoArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class AuthorData {
    private String name;
    private String url;
  }

  @Data
  @NoArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class CoverData {
    private String small;
    private String medium;
    private String large;
  }

  @Data
  @NoArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class PublisherData {
    private String name;
  }
}
