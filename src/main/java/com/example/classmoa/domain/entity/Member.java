package com.example.classmoa.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "platform_id")
    private SocialLoginPlatform socialLoginPlatform;

    private String email;
    private String nickname;
    private String password;
    private Date birthDate;

    @Builder(builderMethodName = "login", buildMethodName = "loginbuild")
    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Builder(builderMethodName = "signup", buildMethodName = "signupbuild")
    public Member(String email, String nickname, String password, Date birthDate) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.birthDate = birthDate;
    }
}
