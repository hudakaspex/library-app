package com.project.libraryManagement.services;

import com.project.libraryManagement.dto.MemberDTO;
import com.project.libraryManagement.dto.PageResponse;
import com.project.libraryManagement.models.core.Member;
import com.project.libraryManagement.repositories.MemberRepository;
import com.project.libraryManagement.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public PageResponse<Member> findByNamePageable(String name, Pageable pageable) {
        Page<Member> page = memberRepository.findByNameContaining(name, pageable);
        PageResponse<Member> pageResponse = new PageResponse<>(page); 
        return pageResponse;
    }

    @Transactional
    public MemberDTO create(Member member) {
        Member savedMember = setMemberName(member);
        Member createdMember = this.memberRepository.save(savedMember);
        return mapToDto(createdMember);
    }

    /**
     * this method used for initial several value for ex. Date Join and Splited member name
     * */
    private Member setMemberName(Member member) {
        member.setSplitName(member.getName());
        return member;
    }

    public List<MemberDTO> findAll() {
        List<Member> members = this.memberRepository.findAll();
        List<MemberDTO> memberDTOS = members.stream().map(member ->
                {
                    return mapToDto(member);
                }).toList();
        return memberDTOS;
    }

    @Transactional
    public MemberDTO update(Long id, Member member) {
        Boolean isMemberExist = this.memberRepository.existsById(id);
        if (isMemberExist) {
            member.setId(id);
            Member updatedNameMember = setMemberName(member);
            Member memberUpdated = this.memberRepository.save(updatedNameMember);
            return mapToDto(memberUpdated);
        }
        else {
            throw new NotFoundException("Data member is not found");
        }
    }

    @Transactional
    public Long delete(Long id) {
        Boolean isMemberExist = this.memberRepository.existsById(id);
        if (isMemberExist) {
            memberRepository.deleteById(id);
            return id;
        }
        else {
            throw new NotFoundException("Data member is not found");
        }
    }
 
    private MemberDTO mapToDto(Member member) {
        return modelMapper.map(member, MemberDTO.class);
    }
}