package com.dev.classmoa.dto.Member.request;

import java.time.LocalDate;

import com.dev.classmoa.domain.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpMemberRequest {

    private String email;
    private String nickname;
    private String password;
    private LocalDate birthDate;

    public Member toEntity() {
        return Member.signup()
                .email(email)
                .nickname(nickname)
                .password(password)
                .birthDate(birthDate)
                .signupbuild();
    }
}
