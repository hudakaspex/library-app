package com.project.libraryManagement.controller;

import com.project.libraryManagement.dto.MemberDTO;
import com.project.libraryManagement.models.core.Member;
import com.project.libraryManagement.services.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("api/members")
public class MemberController {
    private final MemberService memberService;

    MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<MemberDTO> create(@RequestBody Member member) {
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
