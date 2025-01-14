package com.project.libraryManagement.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.libraryManagement.models.core.Placement;

@Repository
public interface PlacementRepository extends JpaRepository<Placement, Long> {
}
