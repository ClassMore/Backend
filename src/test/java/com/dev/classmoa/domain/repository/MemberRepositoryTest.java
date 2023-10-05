package com.dev.classmoa.domain.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.dev.classmoa.domain.entity.Member;

@SpringBootTest
class MemberRepositoryTest {
	@Autowired
	MemberRepository memberRepository;

	@Test
	@Rollback(value = false)
	void insert(){
		memberRepository.save(
			Member.signup()
				.email("aaa@aaa.com")
				.nickname("aaaa")
				.password("asf;sfgi")
				.signupbuild()
		);
	}

}