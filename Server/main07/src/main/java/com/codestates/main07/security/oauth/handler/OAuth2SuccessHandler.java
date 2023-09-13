package com.codestates.main07.security.oauth.handler;

import com.codestates.main07.security.oauth.handler.GoogleOAuth2SuccessHandler;
import com.codestates.main07.security.oauth.handler.KakaoOAuth2SuccessHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OAuth2SuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final GoogleOAuth2SuccessHandler googleOAuth2SuccessHandler;
    private final KakaoOAuth2SuccessHandler kakaoOAuth2SuccessHandler;

    public OAuth2SuccessHandler(GoogleOAuth2SuccessHandler googleOAuth2SuccessHandler,
                                KakaoOAuth2SuccessHandler kakaoOAuth2SuccessHandler) {
        this.googleOAuth2SuccessHandler = googleOAuth2SuccessHandler;
        this.kakaoOAuth2SuccessHandler = kakaoOAuth2SuccessHandler;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication.getPrincipal() instanceof OidcUser) {
            // Google OAuth2 로그인
            googleOAuth2SuccessHandler.onAuthenticationSuccess(request, response, authentication);
        } else {
            // Kakao OAuth2 로그인
            kakaoOAuth2SuccessHandler.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
