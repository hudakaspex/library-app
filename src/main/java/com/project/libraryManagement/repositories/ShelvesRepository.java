package com.project.libraryManagement.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.libraryManagement.models.core.shelves.Shelves;

@Repository
public interface ShelvesRepository extends JpaRepository<Shelves, Long> {
    Page<Shelves> findByLabelContainingIgnoreCase(String label, Pageable page);
}
