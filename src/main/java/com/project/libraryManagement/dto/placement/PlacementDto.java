package com.project.libraryManagement.dto.placement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlacementDto {
    private Long id;
    private Long level;
    private Long section;
    private BookPlacementDto book;
    private ShelvesPlacementDto shelves;
}
