package com.project.libraryManagement.repositories;
import com.project.libraryManagement.models.core.Loan;
import com.project.libraryManagement.models.enums.LoanStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    @Query("SELECT l FROM Loan l JOIN l.member m WHERE m.name LIKE %:name% AND (l.status= :status OR :status IS NULL) ORDER BY l.endDate DESC")
    Page<Loan> getLoanWithFilter(@Param("name") String name, @Param("status") LoanStatus status ,Pageable page);

    @Modifying
    @Query("UPDATE Loan l SET l.status = :status WHERE l.id = :id")
    Integer updateLoanStatus(@Param("id") Long id, @Param("status") LoanStatus status);

    @Modifying
    @Query("UPDATE Loan l SET l.returnDate = :returnDate WHERE l.id= :id")
    Integer updateReturnDate(@Param("id") Long id, @Param("returnDate") Long returnDate);
}
