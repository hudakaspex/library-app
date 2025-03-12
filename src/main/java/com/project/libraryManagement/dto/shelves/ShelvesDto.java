package com.project.libraryManagement.dto.shelves;

import java.util.List;

import com.project.libraryManagement.dto.placement.PlacementDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShelvesDto {
    private Long id;
    private String label;
    private List<PlacementDto> placements;
}
