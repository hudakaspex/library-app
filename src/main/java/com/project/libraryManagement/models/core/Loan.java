package com.project.libraryManagement.models.core;

import com.project.libraryManagement.models.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date")
    private Calendar startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date")
    private Calendar endDate;

    @OneToMany
    private List<Book> books;

    @Enumerated(value = EnumType.STRING)
    private LoanStatus status;

    @Column(name = "return_date")
    private Date returnDate;

    @OneToOne
    private Librarian librarian;

    @OneToOne
    private Member member;
}
