package com.project.libraryManagement.services;

import com.project.libraryManagement.dto.MemberDTO;
import com.project.libraryManagement.models.core.Member;
import com.project.libraryManagement.repositories.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class MemberTest {
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    Member member;

    @BeforeEach
    public void setUp() {
        log.info("SET UP");
        this.member = new Member();
        this.member.setId(1L);
        this.member.setName("Mifta");
    }

    @Test
    public void testCreateMember() {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName(member.getName());
        memberDTO.setId(member.getId());

        when(modelMapper.map(any(), any())).thenReturn(memberDTO);
        //when
        when(this.memberRepository.save(member)).thenReturn(member);
        //then
        MemberDTO result = this.memberService.create(member);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(member.getId());
    }

    @Test
    public void testUpdateMember() {
        member.setName("UPDATE");
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName(member.getName());
        //WHEN
        when(this.memberRepository.save(member)).thenReturn(member);
        when(this.modelMapper.map(any(), any())).thenReturn(memberDTO);

        //THEN
        MemberDTO memberDTO1 = this.memberService.update(member.getId(), member);

        assertThat(memberDTO1.getName()).isEqualTo("UPDATE");
        verify(memberRepository).save(member);
    }
}
