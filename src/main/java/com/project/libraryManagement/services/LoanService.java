package com.project.libraryManagement.services;

import com.project.libraryManagement.exception.NotFoundException;
import com.project.libraryManagement.models.core.Loan;
import com.project.libraryManagement.repositories.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {
    private final LoanRepository loanRepository;

    LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<Loan> findAll() {
        return this.loanRepository.findAll();
    }

    public Loan create(Loan loan) {
       return this.loanRepository.save(loan);
    }

    public Loan update(Long id, Loan loan) {
        Boolean isLoanExist = this.loanRepository.existsById(id);
        if (isLoanExist) {
            loan.setId(id);
            return this.loanRepository.save(loan);
        }
        else {
            throw new NotFoundException("Loan is not found");
        }
    }

    public Long deleteById(Long id) {
        Boolean isLoanExist = this.loanRepository.existsById(id);
        if (isLoanExist) {
                this.loanRepository.deleteById(id);
                return id;
        }
        else {
            throw new NotFoundException("Loan is not found");
        }
    }
}
