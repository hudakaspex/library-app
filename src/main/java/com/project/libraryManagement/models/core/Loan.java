package com.project.libraryManagement.models.core;
import java.util.ArrayList;
import java.util.List;

import com.project.libraryManagement.models.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    private Long startDate;

    @Column(name = "end_date")
    private Long endDate;

    @Enumerated(value = EnumType.STRING)
    private LoanStatus status;

    @OneToMany(mappedBy = "loan", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<LoanBooks> loanBooks = new ArrayList<LoanBooks>();

    @Column(name = "return_date")
    private Long returnDate;

    @ManyToOne()
    @JoinColumn(name = "librarian_id", nullable = true)
    private Librarian librarian;

    @ManyToOne()
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
}
