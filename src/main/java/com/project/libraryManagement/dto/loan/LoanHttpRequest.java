package com.project.libraryManagement.dto.loan;

import java.util.List;

import com.project.libraryManagement.models.enums.LoanStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanHttpRequest {
    private Long id;
    private List<Long> bookIds;
    private Long memberId;
    private Long librarianId;
    private Long startDate;
    private Long endDate;
    private Long returnDate;
    private LoanStatus status;
}
