package com.bookstack.backend.repository;

import com.bookstack.backend.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PublisherRepository extends JpaRepository<Publisher, UUID> {
    Optional<Publisher> findByName(String name);

    Optional<Publisher> findByWebsite(String website);

    List<Publisher> findByLocation(String location);
}
