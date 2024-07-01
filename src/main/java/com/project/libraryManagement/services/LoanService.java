package com.project.libraryManagement.services;
import com.project.libraryManagement.dto.PageResponse;
import com.project.libraryManagement.exception.NotFoundException;
import com.project.libraryManagement.models.core.Loan;
import com.project.libraryManagement.models.enums.LoanStatus;
import com.project.libraryManagement.repositories.LoanRepository;
import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {
    private final LoanRepository loanRepository;

    LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public PageResponse<Loan> findLoanByMemberName(String name, Pageable page) {
        Page<Loan> pageLoan = loanRepository.findByMemberNameContaining(name, page);
        PageResponse<Loan> pageResponse = new PageResponse<>(pageLoan);
        return pageResponse;
    }

    public List<Loan> findAll() {
        return this.loanRepository.findAll();
    }

    @Transactional()
    public Loan create(Loan loan) {
       loan.setStatus(LoanStatus.BORROWED);
       return this.loanRepository.save(loan);
    }

    @Transactional()
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

    @Transactional()
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
