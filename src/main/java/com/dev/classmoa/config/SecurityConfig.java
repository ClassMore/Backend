package com.dev.classmoa.config;

import com.dev.classmoa.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MemberService memberService;

    @Value("${jwt.secret}")
    private String secretKey;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic(AbstractHttpConfigurer::disable) // HttpBasic 비활성화
                .csrf(AbstractHttpConfigurer::disable) // csrf 비활성화
                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //세선 쓰지 않겠다 설정
                .cors(withDefaults())
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/user/**").authenticated()
                        .requestMatchers("/**", "/join",
                                "/login", "/lecture") //.hasAnyRole("USER")
                        .permitAll())
                .addFilterBefore(new JwtFilter(memberService, secretKey), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}