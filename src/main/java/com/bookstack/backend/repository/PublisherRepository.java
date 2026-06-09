package com.bookstack.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PublisherRepository extends JpaRepository<Book, UUID> {
    List<Publisher> findByCountry(String country);
    Optional<Publisher> findByEmail(String email);
}
