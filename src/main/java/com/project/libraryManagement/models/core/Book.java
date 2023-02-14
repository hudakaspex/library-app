package com.project.libraryManagement.models.core;

import com.project.libraryManagement.models.enums.BookType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Book {

   @Id()
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   private String title;

   @ManyToOne
   private Author author;

   private String publicationDate;

   private String ISBN;

   private BookType type;
}
