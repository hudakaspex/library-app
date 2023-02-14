package com.project.libraryManagement.models.core;

import com.project.libraryManagement.models.enums.LoanStatus;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date startDate;

    private Date endDate;

    @OneToOne
    private Book book;

    private LoanStatus status;

    private Date returnDate;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Librarian librarian;
}
