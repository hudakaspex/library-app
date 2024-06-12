package com.project.libraryManagement.controller;

import com.project.libraryManagement.dto.MemberDTO;
import com.project.libraryManagement.dto.PageResponse;
import com.project.libraryManagement.models.core.Member;
import com.project.libraryManagement.services.MemberService;
import jakarta.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "http://localhost:4200")
@RestController()
@RequestMapping("api/members")
public class MemberController {
    private final MemberService memberService;

    MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/search")
    public ResponseEntity<PageResponse<Member>> findByNamePageable(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "0") Integer pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        PageResponse<Member> members = this.memberService.findByNamePageable(name, pageRequest);
        return ResponseEntity.ok(members);
    }

    @PostMapping
    public ResponseEntity<MemberDTO> create(@Valid @RequestBody Member member) {
        MemberDTO memberDTO = this.memberService.create(member);
        return ResponseEntity.ok(memberDTO);
    }

    @GetMapping
    public ResponseEntity<List<MemberDTO>> findAll() {
        List<MemberDTO> memberDTOS = this.memberService.findAll();
        return ResponseEntity.ok(memberDTOS);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDTO> update(@PathVariable Long id, @Valid @RequestBody Member member) {
        MemberDTO memberDTO = this.memberService.update(id, member);
        return ResponseEntity.ok(memberDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Long memberId = memberService.delete(id);
        return ResponseEntity.ok(memberId);
    }
}
