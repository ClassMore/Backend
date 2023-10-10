package com.dev.classmoa.dto.Member.request;

import com.dev.classmoa.domain.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginRequest {
	private String email;
	private String password;

	public Member toEntity(){
		return Member.login()
			.memberName(email)
			.password(password)
			.loginbuild();
	}
}
