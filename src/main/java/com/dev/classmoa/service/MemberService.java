package com.dev.classmoa.service;

import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.domain.entity.SocialLoginPlatform;
import com.dev.classmoa.domain.repository.MemberRepository;
import com.dev.classmoa.dto.Member.LoggedInMember;
import com.dev.classmoa.dto.Member.response.KakaoUserInfoResponse;
import com.dev.classmoa.dto.Member.response.MyPageResponse;
import com.dev.classmoa.exception.ClassmoaException;
import com.dev.classmoa.exception.type.ClassmoaErrorCode;
import com.dev.classmoa.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;
    private final SocialLoginPlatformService platformService;

    @Value("${jwt.secret}")
    private String secretKey;
    private Long expireTimeMs = 1000 * 60 * 30L;

    @Transactional(readOnly = true)
    public Member findMemberByMemberName(String memberName) {
        return memberRepository.findByMemberName(memberName)
                .orElseGet(Member::new);
    }

    @Transactional(readOnly = true)
    public Member findMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new ClassmoaException(ClassmoaErrorCode.NOT_FOUND_MEMBER));
    }

    //TODO: platform 로직 수정
    @Transactional
    public Member createKakaoUser(KakaoUserInfoResponse userInfo) {
        SocialLoginPlatform platform = platformService.findSocialPlatform("kakao");

        System.out.println(userInfo);
        Member kakaoMember = Member.signup()
                .memberName(userInfo.getKakao_account().getEmail())
                .password(encoder.encode(userInfo.toString()))
                .nickname(userInfo.getKakao_account().getProfile().getNickname())
                .platform(platform)
                .signupbuild();

        return memberRepository.save(kakaoMember);
    }

    @Transactional
    public Map<String, Object> checkRegistration(KakaoUserInfoResponse userInfo) throws IOException {
        Member oauthMember = memberRepository.findByMemberName(userInfo.getKakao_account().getEmail())
                .orElseGet(() -> createKakaoUser(userInfo));
        Map<String, Object> info = new HashMap<>();
        info.put("token", JwtUtil.createToken(oauthMember, secretKey, expireTimeMs));
        info.put("member", oauthMember);
        return info;
    }

    @Transactional
    public void join(Member joinMember) {
        // memberName 중복 check
        memberRepository.findByMemberName(joinMember.getMemberName())
                .ifPresent(member -> {
                    throw new ClassmoaException(ClassmoaErrorCode.ALREADY_EXIST_MEMBER);
                });

        SocialLoginPlatform platform = platformService.findSocialPlatform("classmoa");

        // 저장
        //TODO: 회원가입 필드 추가
        Member member = Member.signup()
                .memberName(joinMember.getMemberName())
                .password(encoder.encode(joinMember.getPassword()))
                .nickname(joinMember.getNickname())
                .platform(platform)
                .signupbuild();
        memberRepository.save(member);
    }

    @Transactional
    public Map<String, Object> login(String memberName, String password) throws IOException {
        // userName 없음
        Member selectedMember = memberRepository.findByMemberName(memberName)
                .orElseThrow(() -> new ClassmoaException(ClassmoaErrorCode.NOT_FOUND_MEMBER));

        // password 틀림
        if (!encoder.matches(password, selectedMember.getPassword())) {
            throw new ClassmoaException(ClassmoaErrorCode.NOT_FOUND_MEMBER);
        }
        Map<String, Object> result = new HashMap<>();
        String token = JwtUtil.createToken(selectedMember, secretKey, expireTimeMs);
        result.put("token", token);
        result.put("nickname", selectedMember.getNickname());
        result.put("id", selectedMember.getId());

        // 앞에서 Exception 안났으면 토큰 발행
        return result;
    }

    public MyPageResponse getMemberDetail(LoggedInMember member) {
        Member findedMember = memberRepository.findById(member.getMemberId())
                .orElseThrow(() -> new ClassmoaException(ClassmoaErrorCode.NOT_FOUND_MEMBER));
        return new MyPageResponse(findedMember);
    }
}
