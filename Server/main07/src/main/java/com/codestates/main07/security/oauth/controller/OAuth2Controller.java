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

    // 카카오로부터 액세스 토큰을 얻기 위한 엔드포인트
    @PostMapping("/auth/kakao/token")
    public ResponseEntity<Map<String, Object>> getTokenFromKakao(@RequestBody Map<String, String> payload) {

        String code = payload.get("code");  // 요청 본문에서 인증 코드를 가져옴

        RestTemplate restTemplate = new RestTemplate();  // REST 요청을 위한 스프링의 헬퍼 클래스

        HttpHeaders headers = new HttpHeaders();  // 요청 헤더를 설정하기 위한 객체 생성
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // 카카오 개발자 센터에서 제공받은 API 키와 비밀번호
        String credentials = "REST_API_KEY:REST_API_SECRET";
        // 인증 코드를 이용하여 액세스 토큰을 요청하기 위한 요청 본문 생성
        String body = "grant_type=authorization_code&client_id=REST_API_KEY&redirect_uri=REDIRECT_URI&code=" + code;

        // 요청 객체 생성 (헤더와 본문 포함)
        HttpEntity<String> request = new HttpEntity<>(body, headers);

        // 카카오 토큰 발급 엔드포인트에 POST 요청을 보냄
        ResponseEntity<Map> response = restTemplate.exchange("https://kauth.kakao.com/oauth/token",
                HttpMethod.POST, request, Map.class);

        return ResponseEntity.ok(response.getBody());  // 카카오로부터 받은 응답 반환
    }
}
