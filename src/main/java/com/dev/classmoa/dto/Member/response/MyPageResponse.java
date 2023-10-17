package com.dev.classmoa.dto.Member.response;

import java.time.LocalDate;

import com.dev.classmoa.domain.entity.Member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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

    public MyPageResponse(Member member) {
        this.email = member.getMemberName();
        this.nickname = member.getNickname();
    }
}
