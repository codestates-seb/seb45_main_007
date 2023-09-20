package com.codestates.main07.security.jwt.auth.filter;

import com.codestates.main07.exception.BusinessLogicException;
import com.codestates.main07.exception.ExceptionCode;
import com.codestates.main07.security.jwt.auth.jwt.JwtTokenizer;
import com.codestates.main07.security.jwt.login.LoginDto;
import com.codestates.main07.security.jwt.login.LoginResponseDto;
import com.codestates.main07.member.entity.Member;
import com.codestates.main07.member.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

// Username/Password 기반의 인증을 처리하기 위해 UsernamePasswordAuthenticationFilter를 확장해서 구현
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenizer jwtTokenizer;
    private final MemberRepository memberRepository;

    // DI 받은 AuthenticationManager는 로그인 인증 정보(Username/Password)를 전달받아 UserDetailsService와 인터랙션 한 뒤 인증 여부를 판단
    // DI 받은 JwtTokenizer는 클라이언트가 인증에 성공할 경우, JWT를 생성 및 발급하는 역할
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,
                                   JwtTokenizer jwtTokenizer,
                                   MemberRepository memberRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenizer = jwtTokenizer;
        this.memberRepository = memberRepository;
    }


    @SneakyThrows // 메소드 또는 람다에서 검사된 예외(checked exceptions)를 처리할 때 사용
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

        // 클라이언트에서 전송한 Email과 Password를 DTO 클래스로 역직렬화(Deserialization) 하기 위해 ObjectMapper 인스턴스를 생성
        ObjectMapper objectMapper = new ObjectMapper();

        // ServletInputStream을 LoginDto 클래스의 객체로 역직렬화
        LoginDto loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.class);

        Optional<Member> optionalMember = memberRepository.findByEmail(loginDto.getEmail());

        Member findMember = optionalMember.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        if (findMember.getStatus() == Member.Status.DELETED) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_IS_DELETED);
        }

        //  Email과 Password 정보를 포함한 UsernamePasswordAuthenticationToken을 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        return authenticationManager.authenticate(authenticationToken);  // 인증 처리를 위임
    }

    // 클라이언트의 인증 정보를 이용해 인증에 성공할 경우 호출
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult)  throws ServletException, IOException { // 예외를 던질 수 있도록 명시
        // AuthenticationManager 내부에서 인증에 성공하면 인증된 Authentication 객체가 생성되면서 principal 필드에 Member 객체가 할당됩니다.
        Member member = (Member) authResult.getPrincipal();

        String accessToken = delegateAccessToken(member);   //  Access Token을 생성
        String refreshToken = delegateRefreshToken(member); // Refresh Token을 생성

        // Access Token은 클라이언트 측에서 백엔드 애플리케이션 측에 요청을 보낼 때마다 request header에 추가해서 클라이언트 측의 자격을 증명하는 데 사용
        response.setHeader("Authorization", "Bearer " + accessToken);
        response.setHeader("Refresh", refreshToken);

        // 응답 DTO 생성 및 설정
        LoginResponseDto responseDto = new LoginResponseDto(true, member.getUsername() + "님 반갑습니다.", member.getUsername(), member.getEmail(), member.getMemberId(),member.getNickname());
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(mapper.writeValueAsString(responseDto));

        this.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);
    }

    // (5)와 (6)은 Access Token과 Refresh Token을 생성하는 구체적인 로직
    // (5)
    private String delegateAccessToken(Member member) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("memberId", member.getMemberId()); // 멤버 ID 추가
        claims.put("email", member.getEmail()); // 이메일 추가
        claims.put("roles", member.getRoles());
        claims.put("username", member.getUsername());
        claims.put("nickname", member.getNickname());

        long memberId = member.getMemberId();
        String email = member.getEmail();
        String username = member.getUsername();
        String nickname = member.getNickname();

        if(!claims.containsKey("memberId")) {
            claims.put("memberId", memberId);
        }
        if(!claims.containsKey("email")) {
            claims.put("email", email);
        }
        if (!claims.containsKey("username")) {
            claims.put("username", username);
        }
        if (!claims.containsKey("nickname")) {
            claims.put("nickname", nickname);
        }


        /*
        *회원 등록 시 List<String> authorities 메서드의 멤버 권한 Null 예외가 발생하였다.
        *원인 파악: 왜 member.getRoles()가 null을 반환하는지 확인해야 합니다.
                 member 객체를 DB나 다른 데이터 소스에서 가져오는 로직을 확인하여 roles 필드가 제대로 설정되어 있는지,
                 또는 roles 필드에 기본 값이 제대로 할당되어 있는지 확인해야 합니다.

        *예외 처리 강화: 더 강력한 예외 처리를 통해 이와 같은 문제가 발생했을 때
                     명확한 에러 메시지를 반환하도록 코드를 개선할 수 있습니다.
         */

        // "authorities" 클레임 추가
        List<String> authorities = (member.getRoles() != null ? member.getRoles() : Collections.emptyList())
                .stream()
                .map(role -> "ROLE_" + role)
                .collect(Collectors.toList());
        claims.put("authorities", authorities);

        String subject = member.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String accessToken = jwtTokenizer.generateAccessToken(claims,
                subject,
                expiration,
                base64EncodedSecretKey,
                member.getMemberId(),
                member.getEmail(),
                member.getUsername(),
                authorities);

        return accessToken;
    }

    // (6)
    private String delegateRefreshToken(Member member) {
        String subject = member.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);

        return refreshToken;
    }
}
