package com.project.libraryManagement.services;
import com.project.libraryManagement.dto.PageResponse;
import com.project.libraryManagement.dto.loan.LoanDto;
import com.project.libraryManagement.dto.loan.LoanFilterDTO;
import com.project.libraryManagement.exception.NotFoundException;
import com.project.libraryManagement.models.core.Book;
import com.project.libraryManagement.models.core.Librarian;
import com.project.libraryManagement.models.core.Loan;
import com.project.libraryManagement.models.core.LoanBooks;
import com.project.libraryManagement.models.core.Member;
import com.project.libraryManagement.models.enums.LoanStatus;
import com.project.libraryManagement.repositories.BookRepository;
import com.project.libraryManagement.repositories.LoanBookRepository;
import com.project.libraryManagement.repositories.LoanRepository;
import com.project.libraryManagement.repositories.MemberRepository;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final LoanBookRepository loanBookRepository;
    private final BookRepository bookRepository; 
    private final MemberRepository memberRepository;

    LoanService(
        LoanRepository loanRepository, 
        LoanBookRepository loanBookRepository, 
        BookRepository bookRepository,
        MemberRepository memberRepository) {
        this.loanRepository = loanRepository;
        this.loanBookRepository = loanBookRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    public PageResponse<Loan> getLoanWithFilter(String name, LoanStatus status , Pageable page) {
        Page<Loan> pageLoan = loanRepository.getLoanWithFilter(name, status, page);
        PageResponse<Loan> pageResponse = new PageResponse<>(pageLoan);
        return pageResponse;
    }

    public List<Loan> findAll() {
        return this.loanRepository.findAll();
    }

    @Transactional()
    public Loan create(LoanDto loan) {
        try {
           loan.setStatus(LoanStatus.BORROWED);
           Loan newLoan = generateLoan(loan);
           Loan createdLoan = this.loanRepository.save(newLoan);
           saveLoanBooks(createdLoan, loan.getBookIds());
           return createdLoan;
       } catch (Exception e) {
            throw new RuntimeException("Error while creating loan");
       }
    }

    @Transactional()
    private void saveLoanBooks(Loan loan, List<Long> bookIds) {
       List<LoanBooks> loanBooks = bookIds.stream().map(bookId -> {
           LoanBooks newLoanBook = new LoanBooks();
           Book book = this.bookRepository.findById(bookId).orElseThrow(() -> {
                throw new NotFoundException("Book is not found");
           });
           newLoanBook.setBook(book);
           newLoanBook.setLoan(loan);;
           return newLoanBook;
       }).toList(); 
       try {
           this.loanBookRepository.saveAll(loanBooks);
       } catch (Exception e) {
            throw new RuntimeException("Error while saving loan books");
       }
    }

    private Loan generateLoan(LoanDto loan) {
        Loan newLoan = new Loan();
        Member member = memberRepository.findById(loan.getMemberId()).orElseThrow(() -> {
            throw new NotFoundException("Member is not found");
        });
        newLoan.setMember(member);
        newLoan.setId(loan.getId());
        newLoan.setStartDate(loan.getStartDate());
        newLoan.setEndDate(loan.getEndDate());
        newLoan.setStatus(loan.getStatus());
        newLoan.setReturnDate(loan.getReturnDate());
        newLoan.setStatus(loan.getStatus());
        return newLoan;
    }

    @Transactional()
    public Loan update(Long id, LoanDto loanDto) {
        Optional<Loan> loan  = this.loanRepository.findById(id);
        if (loan.isPresent()) {
            Loan updatedLoan = generateLoan(loanDto);
            this.loanRepository.save(updatedLoan);
            // update loan books
            saveLoanBooks(updatedLoan, loanDto.getBookIds());
            return loan.get();
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
    
    @Transactional()
    public Integer updateStatus(Long id, LoanStatus status) {
        Boolean isLoanExist = this.loanRepository.existsById(id);
        if (isLoanExist) {
            Integer rowsAffected = this.loanRepository.updateLoanStatus(id, status);
            if (rowsAffected > 0) {
              Instant now = Instant.now();
              Long returnDate = now.toEpochMilli();
              rowsAffected = this.loanRepository.updateReturnDate(id, returnDate);         
            }
            return rowsAffected;
        }
        else {
            throw new NotFoundException("Loan is not found");
        }
    }
}
