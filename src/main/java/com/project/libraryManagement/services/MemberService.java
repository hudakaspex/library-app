package com.project.libraryManagement.services;

import com.project.libraryManagement.dto.MemberDTO;
import com.project.libraryManagement.models.core.Member;
import com.project.libraryManagement.repositories.MemberRepository;
import com.project.libraryManagement.utils.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    MemberService(MemberRepository memberRepository, ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }

    public MemberDTO create(Member member) {
        Member createdMember = this.memberRepository.save(member);
        MemberDTO memberDTO = mapToDto(createdMember);
        return memberDTO;
    }

    public List<MemberDTO> findAll() {
        List<Member> members = this.memberRepository.findAll();
        List<MemberDTO> memberDTOS = members.stream().map(member -> mapToDto(member)).toList();
        return memberDTOS;
    }

    public MemberDTO update(Long id, Member member) {
        Member memberUpdated = this.memberRepository.save(member);
        MemberDTO memberDTO = mapToDto(memberUpdated);
        return memberDTO;
    }

    public Long delete(Long id) {
       Member member = this.memberRepository.findById(id)
               .orElseThrow(() -> new NotFoundException("Data member is not found"));
       memberRepository.delete(member);
       return id;
    }

    private MemberDTO mapToDto(Member member) {
        return modelMapper.map(member, MemberDTO.class);
    }
}
