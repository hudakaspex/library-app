package com.project.libraryManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.libraryManagement.models.core.LoanBooks;

@Repository
public interface LoanBookRepository extends JpaRepository<LoanBooks, Long> {
    
    Long deleteByLoanId(@Param("loanId") Long loanId);

    LoanBooks findByLoanId(@Param("loanId") Long loanId);
}
