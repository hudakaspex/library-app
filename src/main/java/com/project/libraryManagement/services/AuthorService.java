package com.project.libraryManagement.services;

import com.project.libraryManagement.dto.PageResponse;
import com.project.libraryManagement.models.core.Author;
import com.project.libraryManagement.repositories.AuthorRepository;
import com.project.libraryManagement.exception.NotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAll() {
        List<Author> authors = this.authorRepository.findAll();
        return authors; 
    }

    public PageResponse<Author> findByNamePageable(String name, Pageable pageable) {
        Page<Author> page = this.authorRepository.findByNameContaining(name, pageable);
        PageResponse<Author> pageResponse = new PageResponse<>(page);
        return pageResponse;
    }   

    @Transactional
    public Author create(Author author) {
        return this.authorRepository.save(author);
    }

    @Transactional
    public Author update(Long id, Author author) {
        Boolean isAuthorExist = authorRepository.existsById(id);
        if (isAuthorExist) {
            author.setId(id);
            return this.authorRepository.save(author);
        }
        else {
            throw new NotFoundException("Author is not found");
        }
    }
    
    @Transactional
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
