package com.dev.classmoa.dto.Member.request;

import java.time.LocalDate;

import com.dev.classmoa.domain.entity.Member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.PastOrPresent;
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

    @Email
    @NotNull
    private String email;

    @NotNull
    private String nickname;

    @Size(min = 2, max = 15)
    @NotNull
    private String password;

}
