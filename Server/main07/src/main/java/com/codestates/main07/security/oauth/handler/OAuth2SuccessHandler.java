package com.codestates.main07.security.oauth.handler;

import com.codestates.main07.member.entity.Member;
import com.codestates.main07.member.repository.MemberRepository;
import com.codestates.main07.member.service.MemberService;
import com.codestates.main07.security.jwt.auth.jwt.JwtTokenizer;
import com.codestates.main07.security.jwt.utils.CustomAuthorityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;


import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component // OAuth2 로그인 인증 후, 성공한 경우 실행되는 핸들러
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    private JwtTokenizer jwtTokenizer; // JWT 토큰을 생성하고 관리하는 컴포넌트

    @Lazy
    @Autowired
    private MemberService memberService;  // 사용자 정보 조회를 위한 서비스

    @Autowired
    private CustomAuthorityUtils customAuthorityUtils;

    @Autowired
    private MemberRepository memberRepository;

    @Override // 사용자가 OAuth2를 통해 성공적으로 인증된 경우 실행되는 메서드
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Map<String, Object> claims = new HashMap<>();

        // 현재 인증된 사용자의 정보를 가져옴
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String email;
        String name;

        // 카카오의 경우 해당 attribute 키를 사용하여 정보를 가져옴
        email = oAuth2User.getAttribute("account_email"); // 사용자가 동의한 경우에만 받을 수 있음
        name = oAuth2User.getAttribute("profile_nickname");

        // DB에서 email을 기준으로 사용자 정보를 조회
        Member member = memberService.findByEmail(email);
        if (member == null) {
            log.error("OAuth2 login failed. No member found with email: " + email);
            // 사용자가 없는 경우 새로운 Member 생성
            member = new Member();
            member.setEmail(email);
        } else {
            member.setEmail(email);
            member.setUsername(name);
        }
        memberRepository.save(member); // 사용자 정보를 저장

        // OAuth2로 로그인한 사용자의 권한을 설정
        Long memberId = member.getMemberId();
        List<GrantedAuthority> authorities = customAuthorityUtils.createAuthorities(email, true);

        // JWT 토큰을 생성하기 위한 필요 정보들을 설정
        String subject = "OAUTH2_USER";
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        // 권한 정보를 문자열로 변환하여 JWT에 포함
        // JwtTokenizer 클래스의 generateAccessToken() 메서드의 알맞는 값을 넣어주기 위함
        List<String> authorityStrings = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // JWT 토큰 생성
        String jwt = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey, memberId, email, name, authorityStrings);

        // 생성된 JWT 토큰을 응답 헤더에 추가
        response.setHeader("Authorization", "Bearer " + jwt);
        log.info("KAKAO OAuth2 login succeeded. JWT token has been set.");
    }
}