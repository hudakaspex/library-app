package com.project.libraryManagement.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.libraryManagement.dto.PageResponse;
import com.project.libraryManagement.models.core.shelves.Shelves;
import com.project.libraryManagement.services.ShelvesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/shelves")
public class ShelvesController {
    private ShelvesService shelvesService;

    ShelvesController(ShelvesService shelvesService) {
        this.shelvesService = shelvesService;
    }

    @GetMapping
    public ResponseEntity<PageResponse<Shelves>> findAll(
        @RequestParam(defaultValue = "") String keyword,
        @RequestParam(defaultValue = "10") Integer pageSize,
        @RequestParam(defaultValue = "0") Integer pageNumber
    ) {
        PageRequest page  = PageRequest.of(pageNumber, pageSize);
        PageResponse<Shelves> response = shelvesService.findAll(keyword, page);
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<Shelves> create(@RequestBody Shelves shelves) {
        Shelves response = this.shelvesService.create(shelves);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Shelves> update(@PathVariable Long id, @RequestBody Shelves shelves) {
        Shelves response = this.shelvesService.update(id, shelves);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Long shelvesId = this.shelvesService.deleteById(id);
        return ResponseEntity.ok(shelvesId);
    }
    
}
