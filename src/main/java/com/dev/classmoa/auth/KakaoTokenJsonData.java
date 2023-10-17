package com.dev.classmoa.auth;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.dev.classmoa.dto.Member.response.KakaoTokenResponse;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class KakaoTokenJsonData {
	private final WebClient webClient;
	private static final String TOKEN_URI = "https://kauth.kakao.com/oauth/token";
	private static final String REDIRECT_URI = "http://localhost:8080/auth/kakao/callback";
	private static final String GRANT_TYPE = "authorization_code";
	private static final String CLIENT_ID = "a915ca187f6f077e4d9078f82a69526f";

	public KakaoTokenResponse getToken(String code) {
		String uri =
			TOKEN_URI + "?grant_type=" + GRANT_TYPE + "&client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI
				+ "&code=" + code;
		System.out.println(uri);

		Flux<KakaoTokenResponse> response = webClient.post()
			.uri(uri)
			.contentType(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToFlux(KakaoTokenResponse.class);

		return response.blockFirst();
	}
}
