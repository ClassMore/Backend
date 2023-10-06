package com.dev.classmoa.service;

import java.util.Optional;

import com.dev.classmoa.exception.ClassmoaException;
import com.dev.classmoa.exception.type.ClassmoaErrorCode;
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
		Member findedMember = memberRepository.findByEmail(member.getEmail())
			.orElseThrow(() -> new ClassmoaException(ClassmoaErrorCode.NOT_FOUND_MEMBER));

		return new MyPageResponse(findedMember);
	}
}
