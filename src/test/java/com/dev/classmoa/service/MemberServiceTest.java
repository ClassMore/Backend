package com.dev.classmoa.service;

import com.dev.classmoa.domain.entity.Lecture;
import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.domain.repository.MemberRepository;
import com.dev.classmoa.exception.ClassmoaException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    public void init(){
    }

    @BeforeEach
    void CreateTestData() {
    }


    @Test
    @DisplayName("회원이 존재 하지 않는다.")
    void getNoMemberDetail() {

        // given
        Member member = Member.login()
                .email("aaa@aaa.com")
                .password("aaa")
                .loginbuild();

        // expected
        assertThatThrownBy(() -> memberService.getMemberDetail(member))
                .isInstanceOf(ClassmoaException.class)
                .hasMessage("존재 하지 않는 회원 입니다.");
    }
}