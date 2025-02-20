package com.project.libraryManagement.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.libraryManagement.dto.PageResponse;
import com.project.libraryManagement.exception.NotFoundException;
import com.project.libraryManagement.models.core.shelves.Placement;
import com.project.libraryManagement.repositories.PlacementRepository;

@Service
public class PlacementService {
    private final PlacementRepository placementRepository;

    PlacementService(PlacementRepository placementRepository) {
        this.placementRepository = placementRepository;
    }

    public Placement findById(Long id) {
        boolean isPlacementExist = this.placementRepository.existsById(id);
        if (isPlacementExist) {
            return this.placementRepository.findById(id).get();
        }
        else {
            throw new NotFoundException("Placement is not found");
        }
    }

    /**
     * Retrieves all Placement entities from the repository.
     *
     * @return a list of all Placement entities.
     */
    public PageResponse<Placement> findAll(Pageable pageable) {
        Page<Placement> page = this.placementRepository.findAll(pageable);
        PageResponse<Placement> response  = new PageResponse<Placement>(page);
        return response;
    }

    /**
     * Creates a new Placement entity and saves it to the repository.
     *
     * @param placement the Placement entity to be created and saved
     * @return the saved Placement entity
     */
    public Placement create(Placement placement) {
        return this.placementRepository.save(placement);
    }

    public Placement update(Long id, Placement placement) {
        boolean isPlacementExist = placementRepository.existsById(id);
        if (isPlacementExist) {
            placement.setId(id);
            return placementRepository.save(placement);
        } else {
            throw new NotFoundException("Placement is not found");
        }
    }

    public Long deleteById(Long id) {
        boolean isPlacementExist = placementRepository.existsById(id);
        if (isPlacementExist) {
            try {
                placementRepository.deleteById(id);
                return id;
            } catch (Exception e) {
                throw e;
            }
        } else {
            throw new NotFoundException("Placement is not found");
        }
    }

}
