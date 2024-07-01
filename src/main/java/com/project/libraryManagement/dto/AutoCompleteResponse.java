package com.project.libraryManagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutoCompleteResponse {
    private Long key;
    private String label;

    public AutoCompleteResponse(String label, Long key) {
        this.label = label;
        this.key = key;
    }
}
