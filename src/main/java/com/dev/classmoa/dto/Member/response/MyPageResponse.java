package com.dev.classmoa.dto.Member.response;

import com.dev.classmoa.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyPageResponse {

    private String email;
    private String nickname;
    private Date birthDate;

    public MyPageResponse(Member member) {
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.birthDate = member.getBirthDate();
    }
}
