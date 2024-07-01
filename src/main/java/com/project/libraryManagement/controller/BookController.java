package com.project.libraryManagement.controller;

import com.project.libraryManagement.dto.AutoCompleteResponse;
import com.project.libraryManagement.dto.PageResponse;
import com.project.libraryManagement.models.core.Book;
import com.project.libraryManagement.services.BookService;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        Book book = bookService.findById(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/autocomplete")
    public ResponseEntity<List<AutoCompleteResponse>> findByTitle(
        @RequestParam(name = "query") String query
    ) {
        List<AutoCompleteResponse> books = bookService.findByTitleIgnoreEmpty(query);
        return ResponseEntity.ok(books);
    }
    
    @GetMapping()
    public ResponseEntity<PageResponse<Book>> getBooks(
            @RequestParam(name = "title", defaultValue = "") String search,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "0") Integer pageNumber
    ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        PageResponse<Book> pageResponse = bookService.findByTitlePageable(search, pageable);
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
