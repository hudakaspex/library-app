package com.project.libraryManagement.services.shelves;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.project.libraryManagement.dto.placement.PlacementDto;
import com.project.libraryManagement.dto.shelves.ShelvesDto;
import com.project.libraryManagement.models.core.shelves.Placement;
import com.project.libraryManagement.models.core.shelves.Shelves;

@Service
public class ShelvesConverter {
    private final ModelMapper modelMapper;

    ShelvesConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ShelvesDto toDto(Shelves shelves) {
        ShelvesDto shelvesDto = new ShelvesDto();
        shelvesDto.setId(shelves.getId());
        shelvesDto.setLabel(shelves.getLabel());
            List<PlacementDto> placements = shelves.getPlacements().stream().map(placement -> {
                return modelMapper.map(placement, PlacementDto.class);
            }).toList();
            shelvesDto.setPlacements(placements);
        return shelvesDto;
    }

}
