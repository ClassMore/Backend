package com.dev.classmoa.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class EmailVerification {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "verification_id")
    private Long id;
    private String email;
    private String verificationCode;
    private Boolean verificationCheck;
    private LocalDateTime sendTime;
    private LocalDateTime expiredTime;

    public EmailVerification(String email, String verificationCode, Boolean verificationCheck, LocalDateTime sendTime, LocalDateTime expiredTime) {
        this.email = email;
        this.verificationCode = verificationCode;
        this.verificationCheck = verificationCheck;
        this.sendTime = sendTime;
        this.expiredTime = expiredTime;
    }
}
