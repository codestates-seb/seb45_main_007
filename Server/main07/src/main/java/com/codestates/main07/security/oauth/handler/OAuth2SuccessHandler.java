package com.codestates.main07.security.oauth.handler;

import com.codestates.main07.security.jwt.auth.jwt.JwtTokenizer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    private JwtTokenizer jwtTokenizer;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        Map<String, Object> claims = new HashMap<>();

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String providerType = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(auth -> auth.startsWith("ROLE_OAUTH2_"))
                .findFirst()
                .orElse("")
                .replaceFirst("ROLE_OAUTH2_", "")
                .toUpperCase();

        String email;
        String name;

        // 여기서 OAuth2 제공자별로 email, name attribute 키를 설정
        switch (providerType) {
            case "GOOGLE":
                email = oAuth2User.getAttribute("email");
                name = oAuth2User.getAttribute("name");
                break;
            case "KAKAO":
                email = oAuth2User.getAttribute("account_email");
                name = oAuth2User.getAttribute("profile_nickname");
                break;
            default:
                throw new IllegalArgumentException("Unsupported provider: " + providerType);
        }

        // JWT 토큰 생성 로직
        String subject = "OAUTH2_USER"; // 예시로 넣은 것입니다. 실제 주제에 맞게 변경해야 합니다.
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        // 일단은 null로 설정하였습니다. 실제로는 해당 정보를 얻어와야 합니다.
        Long memberId = null;
        List<String> authorities = null;

        String jwt = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey, memberId, email, name, authorities);

        response.setHeader("Authorization", "Bearer " + jwt);
        log.info(providerType + " OAuth2 login succeeded. JWT token has been set.");
    }
}