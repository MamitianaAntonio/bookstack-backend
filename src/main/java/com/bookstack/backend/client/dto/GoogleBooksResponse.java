package com.bookstack.backend.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.*;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleBooksResponse {
  private List<Volume> items;

  @Data
  @NoArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Volume {
    private VolumeInfo volumeInfo;
  }

  @Data
  @NoArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class VolumeInfo {
    private String title;
    private List<String> authors;
    private String description;
    private String publishedDate;
    private Integer pageCount;
    private ImageLinks imageLinks;
    private String infoLink;
  }

  @Data
  @NoArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class ImageLinks {
    private String thumbnail;
    private String smallThumbnail;
  }
}
