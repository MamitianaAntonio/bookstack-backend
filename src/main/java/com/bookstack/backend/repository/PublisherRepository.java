package com.bookstack.backend.repository;

import com.bookstack.backend.repository.model.JPublisher;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<JPublisher, String> {
  Optional<JPublisher> findByName(String name);

  Optional<JPublisher> findByWebsite(String website);

  List<JPublisher> findByLocation(String location);

  Optional<JPublisher> findByEmail(String email);

  Boolean existsByEmail(String email);

  Boolean existsByWebsite(String website);

}
