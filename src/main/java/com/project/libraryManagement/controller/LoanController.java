package com.project.libraryManagement.controller;

import com.project.libraryManagement.dto.PageResponse;
import com.project.libraryManagement.dto.UpdateLoanStatusDto;
import com.project.libraryManagement.dto.loan.LoanDto;
import com.project.libraryManagement.dto.loan.LoanHttpRequest;
import com.project.libraryManagement.models.core.Loan;
import com.project.libraryManagement.models.enums.LoanStatus;
import com.project.libraryManagement.services.LoanService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/loans")
@Slf4j
public class LoanController {
    private final LoanService loanService;

    LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/search")
    public ResponseEntity<PageResponse<LoanDto>> findLoanByMemberName(
        @RequestParam(defaultValue = "", name = "name") String memberName,
        @RequestParam(required = false) LoanStatus status,
        @RequestParam(defaultValue = "10") int pageSize,
        @RequestParam(defaultValue = "0") int pageNumber
    ) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        PageResponse<LoanDto> pageResponse = loanService.getLoanWithFilter(memberName, status, pageRequest);
        return ResponseEntity.ok(pageResponse);
    }
    
    @GetMapping()
    public ResponseEntity<List<LoanDto>> findAll() {
        List<LoanDto> loans = loanService.findAll();
        return ResponseEntity.ok(loans);
    } 

    @PostMapping()
    public ResponseEntity<LoanDto> create(@RequestBody LoanHttpRequest loan) {
        LoanDto createdLoan = loanService.create(loan);
        return ResponseEntity.ok(createdLoan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanDto> update(@PathVariable Long id, @RequestBody LoanHttpRequest loan) {
        LoanDto updatedLoan = loanService.update(id, loan);
        return ResponseEntity.ok(updatedLoan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        var deletedId = loanService.deleteById(id);
        return ResponseEntity.ok(deletedId);
    }

    @PostMapping("/updateStatus")
    public ResponseEntity<?> updateStatus(@RequestBody UpdateLoanStatusDto val) {
        Integer id = loanService.updateStatus(val.getId(), val.getStatus());
        return ResponseEntity.ok(id);
    }
}
