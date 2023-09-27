package com.dev.classmoa.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.domain.repository.MemberRepository;
import com.dev.classmoa.dto.Member.response.MyPageResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;
	
	public MyPageResponse getMemberDetail(Member member){
		Member findedMember = memberRepository.findById(member.getId())
			.orElseThrow(() -> new IllegalArgumentException("not found"));

		return new MyPageResponse(findedMember);
	}
}
