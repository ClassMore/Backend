package com.dev.classmoa.dto.Member.request;

import com.dev.classmoa.domain.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberJoinRequest {


    @Email(message = "이메일 형식에 맞지 않습니다.")
    @NotNull(message = "이메일을 입력해 주세요.")
    private String email;

    @Size(min = 2, max = 15, message = "비밀 번호는 2글자 이상 15글자 이하로 입력해 주세요.")
    @NotNull(message = "비밀 번호를 입력해 주세요.")
    private String password;

    @Size(min = 2, max = 15, message = "닉네임은 2글자 이상 15글자 이하로 입력해 주세요.")
    private String nickname;

    public Member toEntity() {
        return Member.signup()
                .memberName(email)
                .password(password)
                .nickname(nickname)
                .signupbuild();
    }
}
