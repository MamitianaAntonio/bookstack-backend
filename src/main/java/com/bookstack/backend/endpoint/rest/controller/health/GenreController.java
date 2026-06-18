package com.bookstack.backend.endpoint.rest.controller.health;

import com.bookstack.backend.model.Genre;
import com.bookstack.backend.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class GenreController {
    private final GenreService service;

    @GetMapping("/genres")
    public List<Genre> getAll(){
        return service.findAll();
    }

    @GetMapping("/genres/{id}")
    public Genre getById(@PathVariable String id){
        return service.findById(id);
    }
}
