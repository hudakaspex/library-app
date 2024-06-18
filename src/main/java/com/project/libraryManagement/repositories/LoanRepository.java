package com.project.libraryManagement.repositories;
import com.project.libraryManagement.models.core.Loan;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    @Query("SELECT l FROM Loan l JOIN l.member m WHERE m.name LIKE %:name%")
    Page<Loan> findByMemberNameContaining(@Param("name") String name, Pageable page);
}
