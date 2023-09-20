package com.project.libraryManagement.models.core;

import com.project.libraryManagement.models.enums.BookType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
@Entity
public class Book {

   @Id()
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @NotBlank(message = "Title is required")
   private String title;

   @OneToOne
   private Author author;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "publication_date")
   private Calendar publicationDate;

   private String ISBN;

   @Enumerated(EnumType.STRING)
   private BookType type;
}
