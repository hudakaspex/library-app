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

    public Author update(Long id, Author author) {
        Boolean isAuthorExist = authorRepository.existsById(id);
        if (isAuthorExist) {
            return this.authorRepository.save(author);
        }
        else {
            throw new NotFoundException("Author is not found");
        }
    }

    public Long delete(Long id) {
        Boolean isAuthorExist = authorRepository.existsById(id);
        if (isAuthorExist) {
            authorRepository.deleteById(id);
            return id;
        }
        else {
            throw new NotFoundException("Author is not found");
        }
    }
}
