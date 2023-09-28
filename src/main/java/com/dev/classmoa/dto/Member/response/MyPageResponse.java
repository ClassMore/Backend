package com.dev.classmoa.dto.Member.response;

import java.time.LocalDate;

import com.dev.classmoa.domain.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyPageResponse {

    private String email;
    private String nickname;
    private LocalDate birthDate;

    public MyPageResponse(Member member) {
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.birthDate = member.getBirthDate();
    }
}
