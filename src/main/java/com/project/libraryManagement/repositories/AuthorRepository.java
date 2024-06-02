package com.project.libraryManagement.repositories;

import com.project.libraryManagement.models.core.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Page<Author> findByNameContaining(String name, Pageable page);

    List<Author> findByNameContaining(String name);
}
