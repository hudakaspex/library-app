package com.project.libraryManagement.dto;

import com.project.libraryManagement.models.enums.LoanStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateLoanStatusDto {
    private Long id;
    private LoanStatus status;
}
