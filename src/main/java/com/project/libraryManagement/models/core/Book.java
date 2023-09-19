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
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String title;

   @OneToOne
   private Author author;

   @Column(name = "publication_date")
   private String publicationDate;

   private String ISBN;

   @Enumerated(EnumType.STRING)
   private BookType type;
}
