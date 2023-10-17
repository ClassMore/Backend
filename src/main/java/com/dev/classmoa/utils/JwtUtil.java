package com.dev.classmoa.utils;

import java.io.IOException;
import java.util.Date;

import com.dev.classmoa.domain.entity.Member;
import com.dev.classmoa.dto.Member.LoggedInMember;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

public class JwtUtil {

    public static LoggedInMember getMember(String token, String secretKey) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        String data = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().get("member", String.class);
        return (LoggedInMember) objectMapper.readValue(data, LoggedInMember.class);
    }

    public static boolean isExpired(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().getExpiration().before(new Date());
    }

    public static String createToken(Member member, String secretKey, long expireTimeMs) throws IOException {
        Claims claims = Jwts.claims(); //일종의 map
        LoggedInMember loggedInMember = new LoggedInMember(member.getId(), member.getMemberName());
        ObjectMapper mapper = new ObjectMapper();

        claims.put("member", mapper.writeValueAsString(loggedInMember));
        return Jwts.builder()
                // payload 에 들어갈 내용
                .setClaims(claims)
                .setIssuedAt(new Date((System.currentTimeMillis()))) // 오늘 날짜
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))
                // signature 에 들어갈 내용 및 서명을 하기 위한 SECRET_KEY
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
