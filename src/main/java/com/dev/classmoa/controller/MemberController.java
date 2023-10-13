package com.dev.classmoa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.dto.Member.response.MyPageResponse;
import com.dev.classmoa.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/mypage")
	public MyPageResponse getMember(Member member){
		return memberService.getMemberDetail(member);
	}

	// TODO: 비밀번호 변경

}
