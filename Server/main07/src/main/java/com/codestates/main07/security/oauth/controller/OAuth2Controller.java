package com.codestates.main07.security.oauth.controller;

import com.codestates.main07.member.entity.Member;
import com.codestates.main07.member.repository.MemberRepository;
import com.codestates.main07.security.oauth.property.KakaoProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@Slf4j
@RestController
public class OAuth2Controller {

    private final KakaoProperties kakaoProperties;
    private final MemberRepository memberRepository;

    public OAuth2Controller(KakaoProperties kakaoProperties, MemberRepository memberRepository) {
        this.kakaoProperties = kakaoProperties;
        this.memberRepository = memberRepository;
    }

    @PostMapping("/auth/kakao/login")
    public String redirectToKakao() {
        return "redirect:/oauth2/authorization/kakao";
    }

//    @PostMapping("/auth/google/login")
//    public String redirectToGoogle() {
//        return "redirect:/oauth2/authorization/google";
//    }

    // 카카오로부터 액세스 토큰을 얻기 위한 엔드포인트
    @PostMapping("/auth/kakao/token")
    public ResponseEntity<Map<String, Object>> getTokenFromKakao(@RequestBody Map<String, String> payload) {

        String code = payload.get("code");  // 요청 본문에서 인증 코드를 가져옴

        RestTemplate restTemplate = new RestTemplate();  // REST 요청을 위한 스프링의 헬퍼 클래스

        HttpHeaders headers = new HttpHeaders();  // 요청 헤더를 설정하기 위한 객체 생성
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // 환경 변수로부터 값을 가져오는 대신, KakaoProperties 클래스로부터 값을 가져옴
        String credentials = kakaoProperties.getClientId() + ":" + kakaoProperties.getClientSecret();
        String redirectUri = kakaoProperties.getRedirectUri();

        // 인증 코드를 이용하여 액세스 토큰을 요청하기 위한 요청 본문 생성
        String body = "grant_type=authorization_code&client_id=" + kakaoProperties.getClientId() +
                "&redirect_uri=" + redirectUri + "&code=" + code;

        // 요청 객체 생성 (헤더와 본문 포함)
        HttpEntity<String> request = new HttpEntity<>(body, headers);

        // 카카오 토큰 발급 엔드포인트에 POST 요청을 보냄
        ResponseEntity<Map> response = restTemplate.exchange("https://kauth.kakao.com/oauth/token",
                HttpMethod.POST, request, Map.class);

        return ResponseEntity.ok(response.getBody());  // 카카오로부터 받은 응답 반환
    }

    @PostMapping("/auth/kakao/userinfo")
    public ResponseEntity<Map<String, Object>> getUserInfoFromKakao(@RequestBody Map<String, String> payload) {
        String accessToken = payload.get("access_token");  // 받은 액세스 토큰을 가져옴
        log.debug("Received access token: {}", accessToken);  // 액세스 토큰을 로그에 출력

        RestTemplate restTemplate = new RestTemplate(); // REST 요청을 위한 스프링의 헬퍼 클래스
        HttpHeaders headers = new HttpHeaders(); // 요청 헤더를 설정하기 위한 객체 생성
        headers.add("Authorization", "Bearer " + accessToken);  // 액세스 토큰을 헤더에 추가
        HttpEntity<String> request = new HttpEntity<>(headers);

        // 카카오 사용자 정보 가져오기 엔드포인트에 GET 요청을 보냄
        ResponseEntity<Map> response = restTemplate.exchange("https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET, request, Map.class);

        Map<String, Object> userInfo = response.getBody();
        log.debug("Received user info: {}", userInfo);  // 사용자 정보를 로그에 출력

        // 사용자 정보를 가지고 멤버 객체를 생성
        Member member = createMemberFromUserInfo(userInfo);

        // 멤버 객체를 DB에 저장
        memberRepository.save(member);

        return ResponseEntity.ok(userInfo);  // 카카오로부터 받은 사용자 정보 반환
    }

    private Member createMemberFromUserInfo(Map<String, Object> userInfo) {
        // 사용자 정보에서 필요한 정보 추출
        String email = (String) userInfo.get("email");
        String nickname = (String) userInfo.get("nickname");

        // 멤버 객체 생성 및 필드 설정
        Member member = new Member();
        member.setEmail(email);
        member.setNickname(nickname);


        return member;
    }
}