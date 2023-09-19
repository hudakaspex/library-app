package com.project.libraryManagement.services;

import com.project.libraryManagement.models.core.Author;
import com.project.libraryManagement.repositories.AuthorRepository;
import com.project.libraryManagement.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    private AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author create(Author author) {
        return this.authorRepository.save(author);
    }

    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    public Author update(Author author) {
        return this.authorRepository.save(author);
    }

    public Long delete(Long id) {
        Author author = this.authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Author is not found"));
        authorRepository.delete(author);
        return id;
    }
}
