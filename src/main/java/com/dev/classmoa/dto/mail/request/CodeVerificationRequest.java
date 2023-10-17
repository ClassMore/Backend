package com.dev.classmoa.dto.mail.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CodeVerificationRequest {
    private String email;
    private String authCode;
}
