package com.project.libraryManagement.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.libraryManagement.dto.PageResponse;
import com.project.libraryManagement.exception.NotFoundException;
import com.project.libraryManagement.models.core.shelves.Shelves;
import com.project.libraryManagement.repositories.ShelvesRepository;

import jakarta.transaction.Transactional;

@Service
public class ShelvesService {
    private ShelvesRepository shelvesRepository;

    ShelvesService(ShelvesRepository shelvesRepository) {
        this.shelvesRepository = shelvesRepository;
    }

    public PageResponse<Shelves> findAll(Pageable pageable) {
        Page<Shelves> page =  this.shelvesRepository.findAll(pageable);
        PageResponse<Shelves> pageResponse = new PageResponse<Shelves>(page);
        return pageResponse;
    }

    public Shelves create(Shelves shelves) {
        return this.shelvesRepository.save(shelves);
    }

    @Transactional
    public Shelves update(Long id, Shelves shelves) {
        boolean isExist = this.shelvesRepository.existsById(id);
        if (isExist) {
            shelves.setId(id);
            return this.shelvesRepository.save(shelves);
        }
        else {
            throw new NotFoundException("Shelves with id {id} is not found");
        }
    }

    public Long deleteById(Long id) {
        boolean isExist = this.shelvesRepository.existsById(id);
        if (isExist) {
            this.shelvesRepository.deleteById(id);
            return id;
        }
        else {
            throw new NotFoundException("Shelves with id {id} is not found");
        }
    }

}
