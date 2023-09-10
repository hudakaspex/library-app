package com.project.libraryManagement.models.core;

import com.project.libraryManagement.models.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date startDate;

    private Date endDate;

    @OneToMany
    private List<Book> books;

    private LoanStatus status;

    private Date returnDate;

    @OneToOne
    private Librarian librarian;

    @OneToOne
    private Member member;
}
