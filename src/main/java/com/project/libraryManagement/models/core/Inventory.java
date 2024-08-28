package com.project.libraryManagement.models.core;

import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Inventory {
    @Id()
    private long id;
    
    private Integer copies;

    @OneToOne
    private Book book;

    private String location;
}
