package com.dev.classmoa.config;

import com.dev.classmoa.dto.Member.LoggedInMember;
import com.dev.classmoa.service.MemberService;
import com.dev.classmoa.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    //    AuthenticationSuccessHandler 규민

    private final MemberService memberService;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorization : {}", authorization);

        // token 없는 상태면 되돌려보냄.
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            log.error("authorization is either null or invalid.");
            filterChain.doFilter(request, response);
            return;
        }

        // Token 꺼내기
        String token = authorization.split(" ")[1];
        log.info("Token : {}", token);

        // Token Expired 되었는지 여부
        if (JwtUtil.isExpired(token, secretKey)) {
            log.error("The token has expired.");
            filterChain.doFilter(request, response);
            return;
        }

        // Token 에서 MemberName 꺼내기
        LoggedInMember memberName = JwtUtil.getMember(token, secretKey);
        log.info("memberName:{}", memberName);

        //        response.setHeader("Authorization", token); 규민

        // 권한 부여
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(memberName, null, List.of(new SimpleGrantedAuthority("MEMBER")));

        // Detail 을 기입
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
