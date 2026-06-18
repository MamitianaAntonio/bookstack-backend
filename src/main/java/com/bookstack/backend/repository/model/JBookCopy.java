package com.bookstack.backend.repository.model;

import com.bookstack.backend.enums.Format;
import com.bookstack.backend.enums.Language;
import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Table(name = "book_copy")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class JBookCopy {
  @Id @UuidGenerator private String id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Format format;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Language language;

  @OneToMany(mappedBy = "bookCopy", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<JPriceHistory> priceHistories;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_id", nullable = false)
  private JBook book;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "publisher_id", nullable = false)
  private JPublisher publisher;

  @OneToMany(mappedBy = "bookCopy", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<JArrivalItem> arrivalItems;

  @OneToMany(mappedBy = "bookCopy", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<JSaleItem> saleItems;
}
