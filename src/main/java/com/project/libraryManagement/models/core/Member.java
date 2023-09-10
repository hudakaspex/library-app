package com.project.libraryManagement.models.core;

import com.project.libraryManagement.models.commons.Person;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Member extends Person {
    @OneToMany
    private List<Loan> loans;
}
