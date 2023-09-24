package com.project.libraryManagement.controller;

import com.project.libraryManagement.models.core.Loan;
import com.project.libraryManagement.services.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
    private final LoanService loanService;

    LoanController(LoanService loanService) {
        this.loanService = loanService;
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
