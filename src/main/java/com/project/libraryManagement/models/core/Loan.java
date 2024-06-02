package com.project.libraryManagement.models.core;

import com.project.libraryManagement.models.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.*;
import java.util.List;

@Setter
@Getter
@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @OneToMany
    @JoinColumn(name = "loan_id", referencedColumnName = "id")
    private List<Book> books;

    @Enumerated(value = EnumType.STRING)
    private LoanStatus status;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @OneToOne
    private Librarian librarian;

    @OneToOne
    private Member member;
}
