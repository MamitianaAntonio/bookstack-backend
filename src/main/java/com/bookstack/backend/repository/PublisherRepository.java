package com.bookstack.backend.repository;

import com.bookstack.backend.entity.Publisher;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, String> {
  Optional<Publisher> findByName(String name);

  Optional<Publisher> findByWebsite(String website);

  List<Publisher> findByLocation(String location);
}
