package com.dev.classmoa.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.classmoa.auth.KakaoTokenJsonData;
import com.dev.classmoa.auth.KakaoUserInfo;
import com.dev.classmoa.auth.annotation.LoginMember;
import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.dto.Member.LoggedInMember;
import com.dev.classmoa.dto.Member.request.MemberJoinRequest;
import com.dev.classmoa.dto.Member.request.MemberLoginRequest;
import com.dev.classmoa.dto.Member.response.KakaoTokenResponse;
import com.dev.classmoa.dto.Member.response.KakaoUserInfoResponse;
import com.dev.classmoa.dto.Member.response.MyPageResponse;
import com.dev.classmoa.dto.mail.request.CodeVerificationRequest;
import com.dev.classmoa.dto.mail.request.EmailCheckRequest;
import com.dev.classmoa.service.MailService;
import com.dev.classmoa.service.MemberService;
import com.dev.classmoa.utils.JwtUtil;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {

	private final KakaoTokenJsonData kakaoTokenJsonData;
	private final KakaoUserInfo kakaoUserInfo;

	private final MemberService memberService;

	private final MailService mailService;

	@Value("${jwt.secret}")
	private String secretKey;
	private Long expireTimeMs = 1000 * 60 * 30L;

	@PostMapping("/join")
	public ResponseEntity<String> join(@RequestBody MemberJoinRequest dto) {
		memberService.join(dto.toEntity());
		return ResponseEntity.ok().body("회원가입이 성공 했습니다.");
	}

	@PostMapping("/login")
	public ResponseEntity<Void> login(@RequestBody MemberLoginRequest dto) throws IOException {
		String token = memberService.login(dto.getEmail(), dto.getPassword());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + token);
		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		return new ResponseEntity<>(headers, HttpStatus.OK);
	}

	@GetMapping("/auth/kakao/callback")
	public void kakaoOauth(@RequestParam("code") String code,
		HttpServletResponse response, HttpServletRequest request)
		throws Exception {
		KakaoTokenResponse kakaoTokenResponse = kakaoTokenJsonData.getToken(code);
		KakaoUserInfoResponse userInfo = kakaoUserInfo.getUserInfo(kakaoTokenResponse.getAccess_token());

		// 비가입자인 경우
		Member originMember = memberService.findMemberByMemberName(userInfo.getKakao_account().getEmail());
		if (originMember.getMemberName() == null) {
			System.out.println("기존 회원이 아니기에 자동 회원가입을 진행합니다.");
			originMember = memberService.createKakaoUser(userInfo);
		}

		// 토큰 생성
		String token = JwtUtil.createToken(originMember, secretKey, expireTimeMs);

		// 토큰을 헤더에 담아 응답
		HttpHeaders headers = new HttpHeaders();
		response.sendRedirect("http://localhost:3000/socialLogin?" + "Bearer " + token);
	}

	@GetMapping("/user/mypage")
	public MyPageResponse getMember(@LoginMember LoggedInMember member) {
		System.out.println(member);
		return memberService.getMemberDetail(member);
	}

	@PostMapping("/emailCheck")
	public ResponseEntity<Void> EmailCheck(@RequestBody EmailCheckRequest request) throws MessagingException,
		UnsupportedEncodingException {
		String authCode = mailService.sendEmail(request);
		//        return authCode;
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/emails/verification")
	public ResponseEntity<Boolean> verificationEmail(@RequestBody CodeVerificationRequest request) {
		Boolean response = mailService.verifiedCode(request);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// TODO: 비밀번호 변경

}
