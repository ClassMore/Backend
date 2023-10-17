package com.dev.classmoa.controller;

import com.dev.classmoa.domain.repository.InterestLectureRepository;
import com.dev.classmoa.domain.repository.MemberRepository;
import com.dev.classmoa.exception.ClassmoaException;
import com.dev.classmoa.exception.type.ClassmoaErrorCode;
import com.dev.classmoa.service.InterestService;
import com.dev.classmoa.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@AutoConfigureMockMvc
@MockBean(JpaMetamodelMappingContext.class)
class MemberControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    MemberService memberService;

    @MockBean
    MemberRepository memberRepository;

    @MockBean
    ClassmoaErrorCode classmoaErrorCode;

    @Test
    @DisplayName("존재 하지 않는 회원의 세부 사항을 조회할 수 없습니다.")
    void getMembersTest() throws Exception{
        //given
        given(memberService.getMemberDetail(any()))
                .willThrow(new ClassmoaException(ClassmoaErrorCode.NOT_FOUND_MEMBER));

        //expected
        mvc.perform(get("/mypage"))
                .andDo(print()).andExpect(status().isNotFound());
    }

}