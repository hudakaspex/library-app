package com.project.libraryManagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.libraryManagement.models.core.Placement;
import com.project.libraryManagement.services.PlacementService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/placement")
public class PlacementController {
    final PlacementService placementService ;

    PlacementController(PlacementService placementService) {
        this.placementService = placementService;
    }

    @GetMapping()
    public ResponseEntity<List<Placement>> findAll() {
        List<Placement> placement = this.placementService.findAll();
        return ResponseEntity.ok(placement);
    }

    @PostMapping()
    public ResponseEntity<Placement> create(@RequestBody Placement placement) {
        Placement result = placementService.create(placement);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Placement> update(@PathVariable Long id, @RequestBody Placement entity) {
        Placement placement = this.placementService.update(id, entity);
        return ResponseEntity.ok(placement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        Long placementId = this.placementService.deleteById(id);
        return ResponseEntity.ok(placementId);
    }
}
