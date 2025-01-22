package com.project.libraryManagement.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.libraryManagement.dto.PageResponse;
import com.project.libraryManagement.models.core.Placement;
import com.project.libraryManagement.services.PlacementService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/placements")
public class PlacementController {
    final PlacementService placementService ;

    PlacementController(PlacementService placementService) {
        this.placementService = placementService;
    }

    @GetMapping()
    public ResponseEntity<PageResponse<Placement>> findAll(
        @RequestParam(defaultValue = "10") Integer pageSize,
        @RequestParam(defaultValue = "0") Integer pageNumber
    ) {
        Pageable pageable = PageRequest.of(pageNumber,  pageSize);
        PageResponse<Placement> placement = this.placementService.findAll(pageable);
        return ResponseEntity.ok(placement);
    }

    @PostMapping()
    public ResponseEntity<Placement> create(@RequestBody Placement placement) {
        Placement result = placementService.create(placement);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Placement> update(@PathVariable Long id, @RequestBody Placement placement) {
        Placement updatedPlacement = this.placementService.update(id, placement);
        return ResponseEntity.ok(updatedPlacement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        Long placementId = this.placementService.deleteById(id);
        return ResponseEntity.ok(placementId);
    }
}
