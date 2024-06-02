package com.project.libraryManagement.services;

import com.project.libraryManagement.dto.PageResponse;
import com.project.libraryManagement.exception.NotFoundException;
import com.project.libraryManagement.models.core.Book;
import com.project.libraryManagement.repositories.BookRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book create(Book book) {
        return this.bookRepository.save(book);
    }

    @Transactional
    public Book update(Long id, Book book) {
        Boolean isBookExist = bookRepository.existsById(id);
        if (isBookExist) {
            book.setId(id);
            return bookRepository.save(book);
        }
        else {
            throw new NotFoundException("Book is not found");
        }
    }

    @Transactional
    public Long deleteById(Long id) {
        Boolean isBookExist = bookRepository.existsById(id);
        if (isBookExist) {
            bookRepository.deleteById(id);
            return id;
        }
        else {
            throw new NotFoundException("Book is not found");
        }
    }
    public PageResponse<Book> findByTitlePageable(String title, Pageable pageRequest) {
        Page<Book> page = this.bookRepository.findByTitleContainingIgnoreCase(title, pageRequest);
        PageResponse<Book> pageResponse = new PageResponse<>(page);
        return pageResponse;
    }
}
