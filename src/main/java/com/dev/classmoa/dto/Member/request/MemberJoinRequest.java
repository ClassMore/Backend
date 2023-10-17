package com.dev.classmoa.dto.Member.request;

import com.dev.classmoa.domain.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberJoinRequest {
	private String email;
	private String password;

	public Member toEntity(){
		return Member.signup()
			.memberName(email)
			.password(password)
			.signupbuild();
	}
}
