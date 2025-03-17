package com.project.libraryManagement.models.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.libraryManagement.models.enums.BookType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
// handling error Type definition error: [simple type, class org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor] becase author is Lazy Load
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Book {

   @Id()
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @NotBlank(message = "Title is required")
   private String title;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "authorId", referencedColumnName = "id")
   private Author author;

   private Long publicationDate;

   @Column(unique = true)
   private String ISBN;

   @Enumerated(EnumType.STRING)
   private BookType type; 

   private Integer copies;
}
