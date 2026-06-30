package com.bookstack.backend.dto;

import com.bookstack.backend.enums.Format;
import com.bookstack.backend.enums.Language;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookCopyRequestDTO {
    private Format format;
    private Language language;
    private String bookId;
    private String publisherId;
}
