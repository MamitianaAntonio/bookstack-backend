package com.bookstack.backend.mapper;

import com.bookstack.backend.model.Publisher;
import com.bookstack.backend.repository.model.JPublisher;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PublisherMapper {
  public List<Publisher> toModel(List<JPublisher> publishers) {
    return publishers.stream().map(this::toModel).toList();
  }

  public Publisher toModel(JPublisher jPublisher) {
    Publisher publisher = new Publisher();
    publisher.setId(jPublisher.getId());
    publisher.setName(jPublisher.getName());
    publisher.setEmail(jPublisher.getEmail());
    publisher.setWebsite(jPublisher.getWebsite());
    publisher.setLocation(jPublisher.getLocation());
    return publisher;
  }

  public List<JPublisher> toEntity(List<Publisher> publishers) {
    return publishers.stream().map(this::toEntity).toList();
  }

  public JPublisher toEntity(Publisher jPublisher) {
    if (jPublisher == null) {
      return null;
    }
    JPublisher publisher = new JPublisher();
    publisher.setId(jPublisher.getId());
    publisher.setName(jPublisher.getName());
    publisher.setEmail(jPublisher.getEmail());
    publisher.setWebsite(jPublisher.getWebsite());
    publisher.setLocation(jPublisher.getLocation());
    return publisher;
  }
}
