package com.dev.classmoa.dto.Member.request;

import com.dev.classmoa.domain.entity.Member;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpMemberRequest {

    private String email;
    private String nickname;
    private String password;
    private Date birthDate;

    public Member toEntity() {
        return Member.signup()
                .email(email)
                .nickname(nickname)
                .password(password)
                .birthDate(birthDate)
                .signupbuild();
    }
}
