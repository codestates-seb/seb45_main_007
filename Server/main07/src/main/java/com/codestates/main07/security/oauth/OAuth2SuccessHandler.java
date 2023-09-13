package com.codestates.main07.security.oauth;

import com.codestates.main07.member.entity.Member;
import com.codestates.main07.member.service.MemberService;
import com.codestates.main07.security.jwt.auth.jwt.JwtTokenizer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
인증 성공 후 호출되는 메서드로,
인증된 사용자의 정보를 바탕으로 JWT 토큰을 생성하고,
이를 응답 헤더에 포함시켜 반환합니다.
 */
@Slf4j
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    @Lazy
    private MemberService memberService;

    @Autowired
    private JwtTokenizer jwtTokenizer;

    // GrantedAuthority : 사용자의 권한을 표현하고 관리하는데 필수적
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        Map<String, Object> claims = new HashMap<>();

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Long memberId = (Long) oAuth2User.getAttribute("memberId");
        String email = oAuth2User.getAttribute("email");
        String username = oAuth2User.getName();

        List<String> authorities = oAuth2User.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        // JWT 토큰 생성
        String jwt = jwtTokenizer.generateAccessToken(
                claims,
                username,
                jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes()),
                jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey()),
                memberId,
                email,
                username,
                authorities
        );

        // JWT를 응답 헤더에 포함시켜 반환
        response.setHeader("Authorization", "Bearer " + jwt);

        log.info("OAuth2 login succeeded. JWT token has been set.");
    }
}
