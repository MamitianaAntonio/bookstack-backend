package com.bookstack.backend.service;

import com.bookstack.backend.mapper.GenreMapper;
import com.bookstack.backend.model.Genre;
import com.bookstack.backend.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreService {
    private final GenreRepository repository;
    private final GenreMapper mapper;

    public List<Genre> findAll(){
        return mapper.toModel(repository.findAll());
    }

    public Genre findById(String id){
        return mapper.toModel(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre with id " + id + " not found")));
    }

}
