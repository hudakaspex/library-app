package com.project.libraryManagement.models.core.shelves;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.libraryManagement.models.core.Book;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = {
    @UniqueConstraint(name = "UniquePlacement", columnNames = {"shelves_id", "section", "level", "book_id"})
})
public class Placement {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long level;

    @Column
    private Long section;

    @OneToOne()
    @JoinColumn(name = "book_id")
    private Book book;

    @JsonBackReference //prevent recursion during JSON serialized from child
    @ManyToOne
    @JoinColumn(name = "shelves_id")
    private Shelves shelves;
}
