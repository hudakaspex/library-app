package com.project.libraryManagement.services;
import com.project.libraryManagement.dto.PageResponse;
import com.project.libraryManagement.dto.loan.LoanHttpRequest;
import com.project.libraryManagement.dto.loan.LoanDto;
import com.project.libraryManagement.exception.NotFoundException;
import com.project.libraryManagement.models.core.Book;
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

    public PageResponse<LoanDto> getLoanWithFilter(String name, LoanStatus status , Pageable page) {
        Page<Loan> pageLoan = loanRepository.getLoanWithFilter(name, status, page);
        Page<LoanDto> pageLoanMap = pageLoan.map(loan -> {
            return mappingLoan(loan);
        });
        PageResponse<LoanDto> pageResponse = new PageResponse<>(pageLoanMap);
        return pageResponse;
    }
    

    public List<LoanDto> findAll() {
        List<LoanDto> loanDtos = this.loanRepository.findAll().stream().map(loan -> {
            return mappingLoan(loan);
        }).toList();
        return loanDtos;
    }

    public LoanDto findById(Long id) {
        Optional<Loan> loan = this.loanRepository.findById(id);
        if (loan.isPresent()) {
            loan.get().getLoanBooks().size(); // initialize loan books
            LoanDto loanDto = mappingLoan(loan.get());
            return loanDto;
        } else {
            throw new NotFoundException("Loan is not found");
        }
    }

    @Transactional()
    public LoanDto create(LoanHttpRequest loan) {
        try {
           loan.setStatus(LoanStatus.BORROWED);
           Loan newLoan = generateLoan(loan);
           Loan createdLoan = this.loanRepository.save(newLoan);
           List<LoanBooks> loanBooks = saveLoanBooks(createdLoan, loan.getBookIds());
           createdLoan.setLoanBooks(loanBooks);
            // Refresh the entity to get latest state 
            //"this is only noted for reference, that when we use flush, we can get the latest state of the entity"
            // this.loanRepository.flush();
            // this.entityManager.refresh(createdLoan); refresh the entity to get the latest state
           LoanDto loanDto = mappingLoan(createdLoan);
           return loanDto;
       } catch (Exception e) {
            throw new RuntimeException("Error while creating loan");
       }
    }

    @Transactional()
    private List<LoanBooks> saveLoanBooks(Loan loan, List<Long> bookIds) {
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
          List<LoanBooks> savedLoanBooks = this.loanBookRepository.saveAll(loanBooks);
          return savedLoanBooks;
       } catch (Exception e) {
            throw new RuntimeException("Error while saving loan books");
       }
    }

    private Loan generateLoan(LoanHttpRequest loan) {
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
    public LoanDto update(Long id, LoanHttpRequest loanDto) {
        Optional<Loan> loan  = this.loanRepository.findById(id);
        if (loan.isPresent()) {
            Loan updatedLoan = generateLoan(loanDto);
            this.loanRepository.save(updatedLoan);
            // update loan books
            return mappingLoan(loan.get());
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

    private LoanDto mappingLoan(Loan loan) {
        LoanDto loanDto = new LoanDto();
        loanDto.setId(loan.getId());
        loanDto.setStartDate(loan.getStartDate());
        loanDto.setEndDate(loan.getEndDate());
        loanDto.setStatus(loan.getStatus());
        loanDto.setReturnDate(loan.getReturnDate());
        loanDto.setMember(loan.getMember());
        List<Book> books = loan.getLoanBooks().stream().map(loanBook -> {
            return loanBook.getBook();
        }).toList();
        loanDto.setBooks(books);
        return loanDto;
    }
}
