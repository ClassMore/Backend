package com.dev.classmoa.dto.Member.request;

import com.dev.classmoa.domain.entity.Member;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginMember {

    private String email;
    private String password;

    public Member toEntity() {
        return Member.login()
                .email(email)
                .password(password)
                .loginbuild();
    }
}