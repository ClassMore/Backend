package com.dev.classmoa.dto.Member.request;

import com.dev.classmoa.domain.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginMemberRequest {

    @Email
    @NotNull
    private String email;

    @Size(min = 2, max = 15)
    @NotNull
    private String password;

}