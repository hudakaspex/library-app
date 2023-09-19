package com.project.libraryManagement.controller;

import com.project.libraryManagement.models.core.Author;
import com.project.libraryManagement.services.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping()
    public ResponseEntity<List<Author>> findAll() {
        List<Author> authors = this.authorService.findAll();
        return ResponseEntity.ok(authors);
    }

    @PostMapping()
    public ResponseEntity<Author> create(@RequestBody() Author author) {
       Author createdAuthor = this.authorService.create(author);
       return ResponseEntity.ok(createdAuthor);
    }

    @PutMapping()
    public ResponseEntity<Author> update(@RequestBody Author author) {
        Author updatedAuthor = authorService.update(author);
        return ResponseEntity.ok(updatedAuthor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Long authorId = authorService.delete(id);
        return ResponseEntity.ok(authorId);
    }
}
