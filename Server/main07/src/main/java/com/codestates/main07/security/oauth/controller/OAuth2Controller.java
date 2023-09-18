package com.codestates.main07.security.oauth.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class OAuth2Controller {

    @PostMapping("/auth/google/login")
    public String redirectToGoogle() {
        return "redirect:/oauth2/authorization/google";
    }

    @PostMapping("/auth/kakao/login")
    public String redirectToKakao() {
        return "redirect:/oauth2/authorization/kakao";
    }

    @PostMapping("/auth/kakao/token")
    public ResponseEntity<Map<String, Object>> getTokenFromKakao(@RequestBody Map<String, String> payload) {
        String code = payload.get("code");

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        String credentials = "REST_API_KEY:REST_API_SECRET"; // 카카오에서 제공받은 REST API KEY와 SECRET
        String body = "grant_type=authorization_code&client_id=REST_API_KEY&redirect_uri=REDIRECT_URI&code=" + code;

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.exchange("https://kauth.kakao.com/oauth/token",
                HttpMethod.POST, request, Map.class);

        return ResponseEntity.ok(response.getBody());
    }
}
