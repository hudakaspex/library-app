package com.project.libraryManagement.services;

import com.project.libraryManagement.models.core.Member;
import com.project.libraryManagement.repositories.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class MemberTest {
    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Test
    void testCreateMember() {
        Member member = new Member();
        member.setId(1L);
        member.setName("Mifta");
        //when
        when(this.memberRepository.save(member)).thenReturn(member);
        //then

    }
}
