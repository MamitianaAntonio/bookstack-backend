package com.bookstack.backend.service;

import com.bookstack.backend.client.dto.GoogleBooksResponse;
import com.bookstack.backend.dto.BookInfoDTO;
import com.bookstack.backend.exception.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@Slf4j
public class BookExternalService {
  private final RestClient restClient = RestClient.create();
  private final ObjectMapper objectMapper = new ObjectMapper();

  public BookInfoDTO getBookInfoDTO(String isbn) {
    BookInfoDTO bookInfo = fetchFromOpenLibrary(isbn);
    if (bookInfo != null) {
      return bookInfo;
    }

    bookInfo = fetchFromGoogleBooks(isbn);
    if (bookInfo != null) {
      return bookInfo;
    }

    throw new NotFoundException("Book with isbn " + isbn + " not found");
  }

  private BookInfoDTO fetchFromOpenLibrary(String isbn) {
    try {
      String url =
          "https://openlibrary.org/api/books?bibkeys=ISBN:" + isbn + "&format=json&jscmd=data";

      String response = restClient.get().uri(url).retrieve().body(String.class);

      var jsonData = objectMapper.readTree(response);
      String key = "ISBN:" + isbn;

      if (jsonData.has(key)) {
        var bookData = jsonData.get(key);

        String title = bookData.path("title").asText("");

        String authors = "";
        var authorData = bookData.path("authors");
        if (authorData.isArray()) {
          authors = String.join(", ", authorData.findValuesAsText("name"));
        }

        String description = bookData.path("description").asText("");

        String publishDate = bookData.path("publish_date").asText("");

        Integer pageCount = bookData.path("page_count").asInt(0);
        if (pageCount == 0) {
          pageCount = null;
        }

        String imageUrl = "";
        var coverData = bookData.path("cover");
        if (!coverData.isMissingNode()) {
          imageUrl = coverData.path("large").asText("");
          if (imageUrl.isEmpty()) {
            imageUrl = coverData.path("medium").asText("");
          }
          if (imageUrl.isEmpty()) {
            imageUrl = coverData.path("small").asText("");
          }
        }

        String openLibraryUrl = bookData.path("url").asText("");

        log.info("Book found in Open Library: {}", title);

        return BookInfoDTO.builder()
            .title(title)
            .authors(authors)
            .isbn(isbn)
            .source("OPEN_LIBRARY")
            .description(description)
            .publishDate(publishDate)
            .pageCount(pageCount)
            .imageUrl(imageUrl)
            .openLibraryUrl(openLibraryUrl)
            .build();
      }
    } catch (Exception e) {
      log.error("Open library error for the ISBN {}: {}", isbn, e.getMessage());
    }
    return null;
  }

  private BookInfoDTO fetchFromGoogleBooks(String isbn) {
    try {
      String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn;

      GoogleBooksResponse response =
          restClient.get().uri(url).retrieve().body(GoogleBooksResponse.class);

      if (response != null && response.getItems() != null && !response.getItems().isEmpty()) {
        var volume = response.getItems().getFirst();
        var info = volume.getVolumeInfo();

        String title = info.getTitle();
        if (title == null) title = "";

        String authors = "";
        if (info.getAuthors() != null) {
          authors = String.join(", ", info.getAuthors());
        }

        String description = info.getDescription();
        if (description == null) description = "";

        String publishDate = info.getPublishedDate();
        if (publishDate == null) publishDate = "";

        Integer pageCount = info.getPageCount();

        String imageUrl = "";
        if (info.getImageLinks() != null) {
          imageUrl = info.getImageLinks().getThumbnail();
          if (imageUrl == null || imageUrl.isEmpty()) {
            imageUrl = info.getImageLinks().getSmallThumbnail();
          }
          if (imageUrl == null) imageUrl = "";
        }

        log.info("Book found in Google books: {}", title);

        return BookInfoDTO.builder()
            .title(title)
            .authors(authors)
            .isbn(isbn)
            .source("GOOGLE_BOOKS")
            .description(description)
            .publishDate(publishDate)
            .pageCount(pageCount)
            .imageUrl(imageUrl)
            .openLibraryUrl(null)
            .build();
      }

    } catch (Exception e) {
      log.error("Google books error for the ISBN {}: {}", isbn, e.getMessage());
    }
    return null;
  }
}
