package com.project.libraryManagement.repositories;

import com.project.libraryManagement.dto.AutoCompleteResponse;
import com.project.libraryManagement.models.core.Book;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    @Query("SELECT new com.project.libraryManagement.dto.AutoCompleteResponse(b.title, b.id) FROM Book b WHERE b.title LIKE CONCAT(:title, '%')")
    List<AutoCompleteResponse> findByTitleStartWith(@Param("title") String title);

    @Query("SELECT new com.project.libraryManagement.dto.AutoCompleteResponse(b.title, b.id) FROM Book b left join Placement p ON b.id = p.book.id WHERE b.title LIKE CONCAT('%', :title, '%') AND p.book.id IS NULL")
    List<AutoCompleteResponse> findByTitleNotInPlacement(@Param("title") String title);

    @Query("SELECT new com.project.libraryManagement.dto.AutoCompleteResponse(b.title, b.id) FROM Book b LEFT JOIN LoanBooks lb ON b.id = lb.book.id "+
    "LEFT JOIN Loan l ON l.id = lb.loan.id "+
    "WHERE b.title LIKE CONCAT('%', :title, '%') AND (lb.book.id IS NULL OR l.status != 'BORROWED')")
    List<AutoCompleteResponse> findByTitleNotInLoanBook(@Param("title") String title);

}
