package com.project.libraryManagement.controller;

import com.project.libraryManagement.models.core.Book;
import com.project.libraryManagement.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public ResponseEntity<List<Book>> findAll() {
        List<Book> books = this.bookService.findAll();
        return ResponseEntity.ok(books);
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book) {
        Book bookCreated = this.bookService.create(book);
        return ResponseEntity.ok(bookCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book) {
        Book bookUpdated = this.bookService.update(id, book);
        return ResponseEntity.ok(bookUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) {
        Long updatedId = bookService.deleteById(id);
        return ResponseEntity.ok(updatedId);
    }
}
