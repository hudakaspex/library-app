package com.project.libraryManagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PageResponse<T> {
    private List<T> data;
    private Long total;

    @SuppressWarnings("unchecked")
    public PageResponse(@SuppressWarnings("rawtypes") Page page) {
        data = page.getContent();
        total = page.getTotalElements();
    }
}
