package com.project.libraryManagement.controller;

import com.project.libraryManagement.dto.PageResponse;
import com.project.libraryManagement.models.core.Book;
import com.project.libraryManagement.services.BookService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public ResponseEntity<PageResponse<Book>> getBooks(
            @RequestParam(name = "search", defaultValue = "") String search,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "0") Integer pageNumber
    ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        PageResponse<Book> pageResponse = bookService.findByTitle(search, pageable);
        return ResponseEntity.ok(pageResponse);
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
