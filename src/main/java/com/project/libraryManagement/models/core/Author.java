package com.project.libraryManagement.models.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.libraryManagement.models.commons.Person;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
// avoid cyc
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Author extends Person {
}
