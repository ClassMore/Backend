package com.dev.classmoa.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.dev.classmoa.dto.Member.response.LoginResponse;
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


	@PostMapping("/join")
	public ResponseEntity<String> join(@RequestBody MemberJoinRequest dto) {
		memberService.join(dto.toEntity());
		return ResponseEntity.ok().body("회원가입이 성공 했습니다.");
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody MemberLoginRequest dto) throws IOException {
		Map<String, Object> map = memberService.login(dto.getEmail(), dto.getPassword());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + map.get("token"));
		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		LoginResponse response = new LoginResponse((String)map.get("nickname"), (Long)map.get("id"));
		return new ResponseEntity<>(response, headers, HttpStatus.OK);
	}

	@GetMapping("/auth/kakao/callback")
	public void kakaoOauth(@RequestParam("code") String code,
		HttpServletResponse response, HttpServletRequest request)
		throws Exception {
		KakaoTokenResponse kakaoTokenResponse = kakaoTokenJsonData.getToken(code);
		KakaoUserInfoResponse userInfo = kakaoUserInfo.getUserInfo(kakaoTokenResponse.getAccess_token());

		Map<String, Object> info = memberService.checkRegistration(userInfo);

		String path = "http://localhost:3000/socialLogin?" + (String)info.get("token") + "&";
		String nickname = URLEncoder.encode(userInfo.getKakao_account().getProfile().getNickname(),
				StandardCharsets.UTF_8);
		Long id = ((Member)info.get("member")).getId();
		System.out.println(path + nickname + "&" + id);
		response.sendRedirect(path + nickname + "&" + id);
	}

	@GetMapping("/user/mypage")
	public MyPageResponse getMember(@LoginMember LoggedInMember member) {
		System.out.println(member);
		return memberService.getMemberDetail(member);
	}
}
