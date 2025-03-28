package com.project.libraryManagement.dto.loan;

import java.util.List;

import com.project.libraryManagement.models.core.Book;
import com.project.libraryManagement.models.core.Member;
import com.project.libraryManagement.models.enums.LoanStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanDto {
    private Long id;
    private Long startDate;
    private Long endDate;
    private LoanStatus status;
    private List<Book> books;
    private Long returnDate;
    private Member member;
}
