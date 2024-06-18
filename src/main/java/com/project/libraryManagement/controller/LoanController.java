package com.project.libraryManagement.controller;

import com.project.libraryManagement.dto.PageResponse;
import com.project.libraryManagement.models.core.Loan;
import com.project.libraryManagement.services.LoanService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/loans")
public class LoanController {
    private final LoanService loanService;

    LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/search")
    public ResponseEntity<PageResponse<Loan>> findLoanByMemberName(
        @RequestParam(defaultValue = "", name = "name") String memberName,
        @RequestParam(defaultValue = "10") int pageSize,
        @RequestParam(defaultValue = "0") int pageNumber
    ) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        PageResponse<Loan> pageResponse = loanService.findLoanByMemberName(memberName, pageRequest);
        return ResponseEntity.ok(pageResponse);
    }
    
    @GetMapping()
    public ResponseEntity<List<Loan>> findAll() {
        List<Loan> loans = loanService.findAll();
        return ResponseEntity.ok(loans);
    } 

    @PostMapping()
    public ResponseEntity<Loan> create(@RequestBody Loan loan) {
        Loan createdLoan = loanService.create(loan);
        return ResponseEntity.ok(createdLoan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Loan> update(@PathVariable Long id, @RequestBody Loan loan) {
        Loan updatedLoan = loanService.update(id, loan);
        return ResponseEntity.ok(updatedLoan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        var deletedId = loanService.deleteById(id);
        return ResponseEntity.ok(deletedId);
    }
}
