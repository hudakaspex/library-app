package com.project.libraryManagement.models.core;
import com.project.libraryManagement.models.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@Entity
// to prevent cyclic references if you're using Jackson for JSON serialization
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    private Long startDate;

    @Column(name = "end_date")
    private Long endDate;

    @OneToMany
    @JoinColumn(name = "loan_id", referencedColumnName = "id")
    private List<Book> books;

    @Enumerated(value = EnumType.STRING)
    private LoanStatus status;

    @Column(name = "return_date")
    private Long returnDate;

    @ManyToOne()
    @JoinColumn(name = "librarian_id", nullable = true)
    private Librarian librarian;

    @ManyToOne()
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
}
