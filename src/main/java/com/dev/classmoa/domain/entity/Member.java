package com.dev.classmoa.domain.entity;

import static jakarta.persistence.FetchType.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "platform_id")
	private SocialLoginPlatform socialLoginPlatform;

	@Column(name = "email")
	private String memberName;
	private String nickname;
	private String password;

	@Builder
	public Member(Long id, String memberName) {
		this.id = id;
		this.memberName = memberName;
	}

	@Builder(builderMethodName = "login", buildMethodName = "loginbuild")
	public Member(String memberName, String password) {
		this.memberName = memberName;
		this.password = password;
	}

	@Builder(builderMethodName = "signup", buildMethodName = "signupbuild")
	public Member(String memberName, String nickname, String password, SocialLoginPlatform platform) {
		this.memberName = memberName;
		this.nickname = nickname;
		this.password = password;
		this.socialLoginPlatform = platform;
	}
}
