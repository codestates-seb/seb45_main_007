package com.codestates.main07.security.oauth.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.yml")
public class KakaoProperties {

    @Value("${kakao.client-id}")
    private String restApiKey;

    @Value("${kakao.client-secret}")
    private String restApiSecret;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    // Getter 메서드
    public String getRestApiKey() {
        return restApiKey;
    }

    public String getRestApiSecret() {
        return restApiSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }
}
