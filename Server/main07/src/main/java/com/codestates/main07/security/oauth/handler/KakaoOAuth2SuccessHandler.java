package com.codestates.main07.security.oauth.handler;

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

@Slf4j
@Component
public class KakaoOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    @Lazy
    private MemberService memberService;

    @Autowired
    private JwtTokenizer jwtTokenizer;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        Map<String, Object> claims = new HashMap<>();

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        // 카카오에서 제공하는 사용자 정보 항목에 따라서 변경해야 할 수 있습니다.
        String email = oAuth2User.getAttribute("account_email"); // 카카오계정(이메일)
        String nickname = oAuth2User.getAttribute("profile_nickname"); // 닉네임

        List<String> authorities = oAuth2User.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        // JWT 토큰 생성
        String jwt = jwtTokenizer.generateAccessToken(
                claims,
                nickname,
                jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes()),
                jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey()),
                null, // 카카오에서 memberId가 제공되지 않는다면 이 값을 null로 두거나 다른 값을 설정
                email,
                nickname,
                authorities
        );

        // JWT를 응답 헤더에 포함시켜 반환
        response.setHeader("Authorization", "Bearer " + jwt);

        log.info("Kakao OAuth2 login succeeded. JWT token has been set.");
    }
}
