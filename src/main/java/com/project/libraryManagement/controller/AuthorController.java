package com.project.libraryManagement.controller;

import com.project.libraryManagement.dto.PageResponse;
import com.project.libraryManagement.models.core.Author;
import com.project.libraryManagement.services.AuthorService;
import jakarta.validation.Valid;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;
    
    AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping()
    public ResponseEntity<List<Author>> getAll() {
        List<Author> authors = this.authorService.getAll();
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/search")
    public ResponseEntity<PageResponse<Author>> findByNamePageable(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "0") Integer pageNumber
    ) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        PageResponse<Author> authors = this.authorService.findByNamePageable(name, pageRequest);
        return ResponseEntity.ok(authors);
    }

    @PostMapping()
    public ResponseEntity<Author> create(@Valid @RequestBody() Author author) {
       Author createdAuthor = this.authorService.create(author);
       return ResponseEntity.ok(createdAuthor);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable Long id, @Valid @RequestBody Author author) {
        Author updatedAuthor = authorService.update(id, author);
        return ResponseEntity.ok(updatedAuthor);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Long authorId = authorService.delete(id);
        return ResponseEntity.ok(authorId);
    }
}
