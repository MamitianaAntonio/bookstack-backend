package com.bookstack.backend.service;

import com.bookstack.backend.mapper.PublisherMapper;
import com.bookstack.backend.model.Publisher;
import com.bookstack.backend.repository.PublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PublisherService {
    private final PublisherRepository publisherRepository;
    private final PublisherMapper mapper;

    public List<Publisher> findAll() {
        return mapper.toModel(publisherRepository.findAll());
    }

    public Publisher findById(String id) {
        return mapper.toModel(
                publisherRepository
                        .findById(id)
                        .orElseThrow(() -> new RuntimeException("Publisher with id " + id + " not found")));
    }

    public Publisher findByName(String name) {
        return mapper.toModel(
                publisherRepository
                        .findByName(name)
                        .orElseThrow(() -> new RuntimeException("Publisher with name " + name + " not found")));
    }

    public boolean existsByEmail(String email) {
        return publisherRepository.existsByEmail(email);
    }

    public Publisher findByEmail(String email) {
        return mapper.toModel(
                publisherRepository
                        .findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("Publisher with email " + email + " not found")));
    }

    public Publisher findByWebsite(String website) {
        return mapper.toModel(
                publisherRepository
                        .findByWebsite(website)
                        .orElseThrow(() -> new RuntimeException("Publisher with website " + website + " not found")));
    }

    //creating many publisher
    public List<Publisher> create(List<Publisher> toSave) {
        return mapper.toModel(publisherRepository.saveAll(mapper.toEntity(toSave)));
    }

    //creating one single publisher
    public Publisher create(Publisher toSave) {
        return mapper.toModel(publisherRepository.save(mapper.toEntity(toSave)));
    }

    public Publisher update(String id, Publisher toUpdate) {
        publisherRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher with id " + id + " not found"));
        toUpdate.setId(id);
        return mapper.toModel(publisherRepository.save(mapper.toEntity(toUpdate)));
    }

    public void delete(String id) {
        publisherRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher with id " + id + " not found"));
        publisherRepository.deleteById(id);
    }
}