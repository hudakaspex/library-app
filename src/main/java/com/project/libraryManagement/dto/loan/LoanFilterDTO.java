package com.project.libraryManagement.dto.loan;

import com.project.libraryManagement.models.enums.LoanStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanFilterDTO {
    String memberName;
    LoanStatus loanStatus;
}
