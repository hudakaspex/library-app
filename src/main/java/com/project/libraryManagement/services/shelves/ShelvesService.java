package com.project.libraryManagement.services.shelves;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.libraryManagement.dto.PageResponse;
import com.project.libraryManagement.dto.shelves.ShelvesDto;
import com.project.libraryManagement.exception.NotFoundException;
import com.project.libraryManagement.models.core.shelves.Shelves;
import com.project.libraryManagement.repositories.ShelvesRepository;

import jakarta.transaction.Transactional;

@Service
public class ShelvesService {
    private final ShelvesRepository shelvesRepository;
    private final ShelvesConverter shelvesConverter;

    ShelvesService(ShelvesRepository shelvesRepository, ShelvesConverter shelvesConverter) {
        this.shelvesRepository = shelvesRepository;
        this.shelvesConverter = shelvesConverter;
    }

    public PageResponse<ShelvesDto> findAll(String label, Pageable pageable) {
        Page<Shelves> page =  this.shelvesRepository.findByLabelContainingIgnoreCase(label, pageable);
        Page<ShelvesDto> pageDto = page.map((Shelves shelves) -> {
            return this.shelvesConverter.toDto(shelves);
        });
        PageResponse<ShelvesDto> pageResponse = new PageResponse<ShelvesDto>(pageDto);
        return pageResponse;
    }

    public ShelvesDto create(Shelves shelves) {
        Shelves shelvesCreated = this.shelvesRepository.save(shelves);
            return this.shelvesConverter.toDto(shelvesCreated);
    }

    @Transactional
    public ShelvesDto update(Long id, Shelves shelves) {
        boolean isExist = this.shelvesRepository.existsById(id);
        if (isExist) {
            shelves.setId(id);
            Shelves updatedShelves = this.shelvesRepository.save(shelves);
            return this.shelvesConverter.toDto(updatedShelves);
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
