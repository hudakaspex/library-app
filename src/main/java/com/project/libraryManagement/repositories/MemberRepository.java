package com.project.libraryManagement.repositories;

import com.project.libraryManagement.models.core.Member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Page<Member> findByNameContaining(String name, Pageable page);
}
