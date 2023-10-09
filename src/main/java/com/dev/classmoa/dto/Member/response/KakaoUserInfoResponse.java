package com.dev.classmoa.dto.Member.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class KakaoUserInfoResponse {
    private Long id;
    private String connected_at;
    private Properties properties;
    private Kakao_account kakao_account;
    @Data
    @JsonIgnoreProperties(ignoreUnknown=true)
    public class Properties {
        private String nickname;
    }
    @Data
    @JsonIgnoreProperties(ignoreUnknown=true)
    public class Kakao_account {
        private Boolean profileNicknameNeedsAgreement;
        private Profile profile;
        private Boolean hasEmail;
        private Boolean emailNeedsAgreement;
        private Boolean isEmailValid;
        private Boolean isEmailVerified;
        private String email;
        private String birthday;
        private String age_range;

        @Data
        @JsonIgnoreProperties(ignoreUnknown=true)
        public class Profile {
            private String nickname;
        }
    }
}