package com.example.classmoa.dto.Member.request;

import com.example.classmoa.domain.entity.Member;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpMember {

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
