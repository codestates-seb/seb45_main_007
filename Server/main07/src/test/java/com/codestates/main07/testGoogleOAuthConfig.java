package com.codestates.main07;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

// 시스템 환경 변수에서 구글 클라이언트 아이디와 시크릿 변수를 잘 가지고 오는지 확인하는 테스트
@SpringBootTest
public class testGoogleOAuthConfig {
    @Value("${GOOGLE_CLIENT_ID}")
    private String googleClientId;

    @Value("${GOOGLE_CLIENT_SECRET}")
    private String googleClientSecret;

    @Test
    public void testGoogleOAuthConfig() {
        assertNotNull(googleClientId);
        System.out.println("Google Client ID: " + googleClientId);

        assertNotNull(googleClientSecret);
        System.out.println("Google Client Secret: " + googleClientSecret);
    }
}
